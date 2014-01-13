package uz.micros;

public class Result<F, R> {
    private F flag;
    private R res;
    private int t;

    public Result(F f, R r) {
        flag = f;
        res = r;
    }

    public Result(F f, R r, int rw) {
        flag = f;
        res = r;
    }

    public F getFlag() {
        return flag;
    }

    public R getRes() {
        return res;
    }
}
