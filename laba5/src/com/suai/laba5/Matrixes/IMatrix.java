package com.suai.laba5.Matrixes;

public interface IMatrix {

    public IMatrix product(IMatrix m);

    public IMatrix sum(IMatrix m);

    public int getRows();
    public int getColumns();

    public int getElement(int row, int col);
    public void setElement(int row, int col, int val);


}
