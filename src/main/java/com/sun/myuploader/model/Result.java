package com.sun.myuploader.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.function.Predicate;


/**
 * 
 * @author andy
 *
 * @param <T>
 */
@Data
@AllArgsConstructor
public class Result<T> {
    private boolean ok;
    private T data;

    public Result(boolean ok, T data) {
		super();
		this.ok = ok;
		this.data = data;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Result() {

    }

    public Result(boolean ok) {
        this(ok, null);
    }

    public Result(T data) {
        this(true, data);
    }

    public static Result OK() {
        return new Result(true);
    }

    public static Result FAIL() {
        return new Result(false);
    }

    public static Result test(Object o, Predicate<Object> p) {
        return p.test(o) ? new Result<>(o) : FAIL();
    }
}