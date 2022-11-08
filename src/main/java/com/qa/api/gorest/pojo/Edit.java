package com.qa.api.gorest.pojo;

public class Edit
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
    
    public Edit(String href)
    {
        super();
        this.href = href;
    }
}
