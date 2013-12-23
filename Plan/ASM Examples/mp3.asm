.include "def.inc"
.include "macros.inc"

.include "C:\avrtools\appnotes\8535def.inc"
;.include "C:\Program Files\Atmel\AVR Studio\Appnotes\8535def.inc"
;.include "c:\ee476\8535def.inc"

.dseg
TOC:  .byte 256

.cseg
.org $0000 
	rjmp 	RESET	;reset entry vector 
	reti		 
	reti 
	reti 
	reti 
	reti 
	rjmp	t1match
	reti 
	reti	 
	reti 
	reti 
	reti 
	reti 
	reti 
	reti 
	reti 
	reti
	 
.include "fstrings.inc" 
RESET:	ldi	temp, LOW(RAMEND) ;setup stack pointer 
	out 	SPL, temp 
	ldi	temp, HIGH(RAMEND) 
	out	SPH, temp
	
	;********************************************** 
	;setup UART -- enable RX, TX pins without ISRs 
	ldi 	temp, 0b00011000 
	out 	UCR, temp
	;set baud rate to 9600 
	ldi	temp, baud96 
	out	UBRR, temp
	;**********************************************

	;set porta to outputs 
	ser 	temp 
	out	DDRA,temp 
	clr 	temp 
	out	PORTA,temp 
	
	;set portb to outputs 
	ser 	temp 
	out	DDRB,temp 
	ser 	temp
	out	PORTB,temp 
	
	;set up portd as inputs
	clr	temp
	out	DDRD,temp
	ser	temp
	out	PORTD,temp
	 
	;set up SPI 
	;ldi	temp,0b01011101 
	ldi	temp,0b01010001 
	out	SPCR,temp 

	;set up Timer 1 to interrupt on compare A match
	;and set the compare time to 62500 ticks
	ldi	temp,0b00010000	;enable t1 matchA interrupt
	out	TIMSK, temp
	
	;test code
	ldi	temp,0xff
	
	ldi	temp, 0x82	;set the match A register to
	out	OCR1AH, temp	;33500 since 33500*.25 microsec=8.375msec
	ldi	temp, 0xdc	;33500 = 0x82dc
	out	OCR1AL, temp

	ldi 	temp,0b00001000	;prescale =1 (one tick=.25 microsec)
	out 	TCCR1B, temp	;and clear-on-matchA

	mov	mmc_state,temp
		
	;initialize variables 
	clr	temp
	mov	zero,temp
	ldi	temp,1
	mov	one,temp
	ldi	temp,3
	mov	dec_read,temp
	mov	frame_count,zero
	mov	frame_old,zero
	mov	current_track,one
	ser	prev_but
	LowerBitEnable
		
	sei

	ldi	sTXchar,'>'
	rcall	putchar
	;rcall	getchar

	rcall	MMCinit
	rcall	ReadTOC
	incADDR
	incADDR

stop:	mov	addr3,zero
	mov	addr2,zero
	mov	addr1,zero
	mov	addr0,zero
	incADDR
	incADDR
	mov	current_track,one	

wait:	sbic	PIND,7	;wait for the play button to be pressed
	rjmp wait
	
restart:
	;initialize decoder here
	rcall 	dec_init		;init sta013 
	
	rcall 	ReadBLOCK
	DecoderWrite 	RUN,1
	DecoderWrite 	PLAY,1
	DecoderWrite	DLA,0x0f
	DecoderWrite	DRA,0x0f
		
	;start timer1
	ldi 	temp,0b00001001	;prescale =1 (one tick=.25 microsec)
	out 	TCCR1B, temp	;and clear-on-matchA

main:	cpse	mmc_state,zero
	rjmp	main	
	rcall	ReadBLOCK

	;set timer1 to 16ms between reads
	ldi	temp, 0xfa	;set the match A register to
	out	OCR1AH, temp	;64000 since 64000*.25 microsec=16msec
	ldi	temp, 0x00	;64000 = 0xfa00
	out	OCR1AL, temp

	sub	dec_read,one
	breq	push_button
	cp	dec_read,one
	breq	frame_count

	rjmp	main

push_button:
	ldi	temp,3
	mov	dec_read,temp
	in	temp,PIND
	cp	temp,prev_but
	brne	check_but
	rjmp	main
	
check_but:
	mov	prev_but,temp
	sbrs	prev_but,6
	rjmp	stop
	sbrs	prev_but,5
	rjmp	prev_track
	sbrs	prev_but,4
	rjmp	next_track
	
	rjmp	main

frame_count:
	DecoderRead	FRAME_CNT_L
	mov	frame_old,frame_count
	mov	frame_count,data_i2c
	cpse	frame_count,frame_old
	rjmp	_end_read
	
	;set timer1 to 16ms between reads
	ldi	temp, 0xfa	;set the match A register to
	out	OCR1AH, temp	;64000 since 64000*.25 microsec=16msec
	ldi	temp, 0x00	;64000 = 0xfa00
	out	OCR1AL, temp

	ldi	sTXchar,'x'
	rcall	putchar

	rjmp	restart
	
_end_read:
	mov	spichar,data_i2c
	rcall	spi_to_uart
	rjmp	main
prev_track:
	cpse	current_track,one	;if at track one,
					;	do not decrement current_track
	sub	current_track,one
	mov	temp,current_track
	lsl	temp
	lsl	temp
	ldi	temp2,3
	sub	temp,temp2

	ldi	ZL,LOW(TOC)
	ldi	ZH,HIGH(TOC)

	add	ZL,temp
	adc	ZH,zero

	ld	addr3,Z+
	ld	addr2,Z+
	ld	addr1,Z+
	mov	addr0,zero
	rjmp	main

next_track:
	cp	current_track,num_tracks	;if not at last track
	brne	_not_last_track			;	proceed as normal
	mov	current_track,zero		;else	
						;	set current track to zero
	
_not_last_track:
	mov	temp,current_track
	add	current_track,one
	lsl	temp
	lsl	temp
	add	temp,one

	ldi	ZL,LOW(TOC)
	ldi	ZH,HIGH(TOC)

	add	ZL,temp
	adc	ZH,zero

	ld	addr3,Z+
	ld	addr2,Z+
	ld	addr1,Z+
	mov	addr0,zero
	rjmp	main


.include "MMC.inc"
.include "i2c.inc"

;**** timer 1 compare A match 1/sec ***************************
t1match:in	save, SREG
	mov	mmc_state,zero
	out	SREG, save
	reti	
;**************************************************************
