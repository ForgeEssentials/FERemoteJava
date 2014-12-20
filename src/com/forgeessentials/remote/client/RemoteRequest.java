package com.forgeessentials.remote.client;

/**
 * Represents a generic remote request
 */
public class RemoteRequest<T> {

    public String id;

    public int rid;

    public RequestAuth auth;

    public T data;

    public RemoteRequest(String id, int rid, RequestAuth auth, T data)
    {
        this.id = id;
        this.rid = rid;
        this.auth = auth;
        this.data = data;
    }

    public RemoteRequest(String id, RequestAuth auth, T data)
    {
        this(id, 0, auth, data);
    }

    public RemoteRequest(String id, T data)
    {
        this(id, null, data);
    }

}
