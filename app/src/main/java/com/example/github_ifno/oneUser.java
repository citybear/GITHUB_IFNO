package com.example.github_ifno;

public class oneUser {

    private String location;
    private String name;
    private String company;
    private String site_admin;
    private String blog;
    private String avatar_url;

    oneUser()
    {
    }

    public void setlocation(String value)
    {
        location = value;
    }

    public void setname(String value)
    {
        name = value;
    }
    public void setcompany(String value)
    {
        company = value;
    }
    public void setsite_admin(String value)
    {
        site_admin = value;
    }
    public void setblog(String value)
    {
        blog = value;
    }
    public void setavatar_url(String value)
    {
        avatar_url = value;
    }




    public String getlocation()
    {
        return location;
    }

    public String getname()
    {
        return name;
    }
    public String getcompany()
    {
        return company;
    }
    public String getsite_admin()
    {
        return site_admin;
    }
    public String getblog()
    {
        return blog;
    }
    public String getavatar_url()
    {
        return avatar_url;
    }

}
