package com.carma.swagger.doclet.sample.api;

public class Recursive {

    private Recursive recursive;

    public Recursive getRecursive() {
        return recursive;
    }

    public void setRecursive(Recursive recursive) {
        this.recursive = recursive;
    }

}
