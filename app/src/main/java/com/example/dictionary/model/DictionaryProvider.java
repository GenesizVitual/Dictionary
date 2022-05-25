package com.example.dictionary.model;

public class DictionaryProvider {

    private String Kosa_kata_bagai;
    private String Kosa_kata_indonesia;
    private String TERJEMAHAN;


    public DictionaryProvider(String banggai, String indonesia, String terjemahan){
        this.Kosa_kata_bagai = banggai;
        this.Kosa_kata_indonesia = indonesia;
        this.TERJEMAHAN = terjemahan;
    }

    public String getKosa_kata_bagai() {
        return Kosa_kata_bagai;
    }

    public void setKosa_kata_bagai(String kosa_kata_bagai) {
        Kosa_kata_bagai = kosa_kata_bagai;
    }

    public String getKosa_kata_indonesia() {
        return Kosa_kata_indonesia;
    }

    public void setKosa_kata_indonesia(String kosa_kata_indonesia) {
        Kosa_kata_indonesia = kosa_kata_indonesia;
    }

    public String getTERJEMAHAN() {
        return TERJEMAHAN;
    }

    public void setTERJEMAHAN(String TERJEMAHAN) {
        this.TERJEMAHAN = TERJEMAHAN;
    }
}
