package com.forgeessentials.remote.client;

/**
 * Represents a generic remote response
 */
public class RemoteResponse<T> {

    public int rid;

    public boolean success;

    public String error;

    public T data;

    public RemoteResponse(int rid, T data)
    {
        this.rid = rid;
        this.success = true;
        this.data = data;
    }

    public RemoteResponse(RemoteResponse<?> response, T data)
    {
        this.rid = response.rid;
        this.success = response.success;
        this.error = response.error;
        this.data = data;
    }

}
