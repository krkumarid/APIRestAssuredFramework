package com.qa.api.gorest.pojo;

public class Avatar
{
    private String href;
    
    public String getHref()
    {
        return href;
    }
    
    public void setHref(String href)
    {
        this.href = href;
    }
    
    public Avatar(String href)
    {
        super();
        this.href = href;
    }
}
