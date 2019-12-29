package srs.subtypes;

public class TypeChoose {
  int idType;
  String name;

  public TypeChoose() {
  }

  public TypeChoose(int idType, String name) {
    this.idType = idType;
    this.name = name;
  }

  public TypeChoose(String name) {
    this.name = name;
  }

  public int getIdType() {
    return idType;
  }

  public void setIdType(int idType) {
    this.idType = idType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
