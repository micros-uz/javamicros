;*****************************************************
;MMC.inc
;functions for interfacing with the MMC
;*****************************************************

SendBYTE:
	out  	spdr,spichar 
;	rcall	spi_to_uart
spiWAIT: 
	sbis 	spsr,7 
	rjmp 	spiWAIT 
	in   	spichar,spdr 
	ret

ResponseWAIT:
	ReadBYTE
	;rcall	spi_to_uart
	subi	temp,1 
	breq	ResponseERROR 
	cp	spichar,temp2 
	breq	ResponseDONE
	cpi	spichar,0x00
	breq	ResponseDONE
	cpi	spichar,0x01
	breq	ResponseDONE
	rjmp	ResponseWAIT
ResponseDONE:
	ret
ResponseERROR:
	;ldi	sTXchar,'w'
	;rcall	putchar
	push	spichar
	mov	spichar,temp2
	;rcall	spi_to_uart
	pop	spichar
	ret
	
;******************************************
;MMCinit:  initialize the MMC card
;Raise SS
;SEND 80 CLOCK CYCLES
;Send command 0 to put in SPI mode
;Send command 1 to ensure MMC is ready
;Send command 16 to set block size to 256 BYTES
;*******************************************	
MMCinit:
	clr	addr3
	clr	addr2
	clr	addr1
	clr	addr0
	RaiseSS
	ReadBYTE
	ReadBYTE
	ReadBYTE
	ReadBYTE
	ReadBYTE
	ReadBYTE
	ReadBYTE
	ReadBYTE
	ReadBYTE
	ReadBYTE
	SendCMD	0,addr3,addr2,addr1,addr0,0x95
	GetRESPONSE	;0x01
	cpi	spichar,0x01
	brne	init_error
init_cmd1:
	ldi	temp,0xff
	SendCMD 1,addr3,addr2,addr1,addr0,0xff
	GetRESPONSE	;0x00
	dec	temp
	cpi	temp,0x00
	breq	init_error
	cpi	spichar,0x00
	brne	init_cmd1
	
	;COMMAND 1 RECEIVED - CARD IS INITIALIZED
	;SEND COMMAND 16 to set BLOCK LENGTH to 256
	ldi addr1,1
	SendCMD 16,addr3,addr2,addr1,addr0,0xff
	GetRESPONSE	;0x00
	cpi	spichar,0x00
	brne	init_error
	
	ldi	sTXchar,'i'
	rcall 	putchar
	ret
init_error:
	ldi	sTXchar,'s'

	cbi	PORTA,0
	sbi	PORTA,1
	
	rcall 	MMCerror
	ret
	
ReadTOC:
	clr	addr3
	clr	addr2
	clr	addr1
	clr	addr0
	ldi 	ZL,LOW(TOC)
	ldi 	ZH,HIGH(TOC)
	SendCMD 17,addr3,addr2,addr1,addr0,0xff
	GetRESPONSE	;0x00
	cpi	spichar,0x00
	brne	read_error_TOC
	GetRESPONSE	;0xfe
	cpi	spichar,0xfe
	brne	read_error_TOC
	
	;read command accepted - read the block
	;raise bit enable - then read 256 bytes
	push 	temp
	ldi	temp,0xff
	ReadBYTE
	st	Z+,spichar
	;rcall	spi_to_uart
TOCblock:
	ReadBYTE
	st	Z+,spichar
	;rcall	spi_to_uart
	dec	temp
	brne	TOCblock
	
	ReadBYTE	;getCRC
	ReadBYTE	;getCRC

	ldi	ZL,LOW(TOC)
	ldi	ZH,HIGH(TOC)
	ld	num_tracks,Z+
	mov	temp,num_tracks
	
	lsl	temp
	lsl	temp
	
	add	ZL,temp
	adc	ZH,zero
	
	ld	end_card3,Z+
	ld	end_card2,Z+
	ld	end_card1,Z+
	ld	end_card0,Z
	
	pop	temp
	ret

read_error_TOC:
	ldi	sTXchar,'T'

	
	rcall 	MMCerror
	pop	temp
	ret

ReadBLOCK:
	SendCMD 17,addr3,addr2,addr1,addr0,0xff
	GetRESPONSE	;0x00
	cpi	spichar,0x00
	brne	read_error
	GetRESPONSE	;0xfe
	ldi	temp2,0xfe
	cp	spichar,temp2
	brne	read_error
	
	;read command accepted - read the block
	;raise bit enable - then read 256 bytes
	push 	temp
	ldi	temp,0xff
	mov	mmc_state,temp
	RaiseBitEnable
	ReadBYTE

	;rcall	spi_to_uart
block:
	ReadBYTE

	;rcall	spi_to_uart
	subi	temp,1
	brne	block
	LowerBitEnable
	
	ReadBYTE	;get CRC
	ReadBYTE	;get CRC
	
	incADDR
	compareADDR
	brge	mem_done
	pop	temp
	ret
		
read_error:
	ldi	sTXchar,'R'
	
	rcall 	MMCerror
	pop	temp
	ret
mem_done:
	ldi	sTXchar,'D'
	rcall	putchar
	pop	temp
	ret
MMCerror:
	rcall	putchar
loop:	rjmp	loop
	ret
;***************************************************
spi_to_uart: 
	push	temp 
	push 	temp2 
	 
	;output data to the uart and format it 
	mov	temp,spichar 
	lsr	temp 
	lsr 	temp		 
	lsr	temp 
	lsr 	temp 
	 
	cpi	temp,0x0a 
	brlt	_sadd30 
	ldi	temp2,('a'-10) 
	rjmp	_snext 
_sadd30:ldi	temp2,'0' 
_snext:	add	temp,temp2 
		 
	mov	sTXchar,temp 
	rcall	putchar 
	mov	temp,spichar 
	andi	temp,0x0f 
		 
	cpi	temp,0x0a 
	brlt	_sadd302 
	ldi	temp2,('a'-10) 
	rjmp	_snext2 
_sadd302: 
	ldi	temp2,'0' 
_snext2:add	temp,temp2 
	mov	sTXchar,temp 
	rcall	putchar 
	;crlf 
	ldi	sTXchar,0x0d	 
	;rcall	_putchar 
	ldi	sTXchar,0x0a 
	;rcall	_putchar 
	pop	temp2 
	pop	temp 
	ret
	
putchar: 
     	sbis 	usr,udre 
     	rjmp 	putchar 
     	out  	udr,sTXchar 
	ret 
getchar: 
     	sbis 	usr,rxc 
     	rjmp 	getchar 
     	in   	sTXchar,udr 
	rcall	putchar 
	ret 
	