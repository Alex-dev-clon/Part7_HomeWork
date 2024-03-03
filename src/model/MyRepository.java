package model;

public interface MyRepository {
    void checkSizeValue(String receivedValue);

    String[] getArrayForWriter(String[] value);

    void writeResultArray(String[] value);
}
