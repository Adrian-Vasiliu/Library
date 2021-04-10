package Domain;

import Exceptions.*;

public class Book {
    private String title;
    private String[] authors;
    private int appearanceYear;
    private float price;
    private String publishing;
    private String quota;

    public Book(String title, String[] authors, int appearanceYear, float price, String publishing,
                String quota) throws YearException, PriceException, QuotaException { // constructor principal
        this.title = title;
        this.authors = authors;
        if (appearanceYear <= 2020)
            this.appearanceYear = appearanceYear;
        else throw new YearException("Anul trebuie sa fie mai mic sau egal cu 2020!");
        if (price > 0)
            this.price = price;
        else throw new PriceException("Pretul trebuie sa aiba o valoare pozitiva!");
        this.publishing = publishing;
        if (quota.length() == 8)
            this.quota = quota;
        else throw new QuotaException("Cota trebuie sa aiba 8 caractere!");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public int getAppearanceYear() {
        return appearanceYear;
    }

    public void setAppearanceYear(int appearanceYear) throws YearException {
        if (appearanceYear <= 2020)
            this.appearanceYear = appearanceYear;
        else throw new YearException("Anul trebuie sa fie mai mic sau egal cu 2020!");
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) throws PriceException {
        if (price > 0)
            this.price = price;
        else throw new PriceException("Pretul trebuie sa aiba o valoare pozitiva!");
    }

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) throws QuotaException {
        if (quota.length() == 8)
            this.quota = quota;
        else throw new QuotaException("Cota trebuie sa aiba 8 caractere!");
    }
}
