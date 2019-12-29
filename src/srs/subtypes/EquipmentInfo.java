package srs.subtypes;

public class EquipmentInfo {

  int ID_equipment;
  String eqName;
  String eqDescription;
  int eqAmount;

  public EquipmentInfo() {
  }

  public EquipmentInfo(int ID_equipment, String eqName, String eqDescription, int eqAmount) {
    this.ID_equipment = ID_equipment;
    this.eqName = eqName;
    this.eqDescription = eqDescription;
    this.eqAmount = eqAmount;
  }

  public int getID_equipment() {
    return ID_equipment;
  }

  public void setID_equipment(int ID_equipment) {
    this.ID_equipment = ID_equipment;
  }

  public String getEqName() {
    return eqName;
  }

  public void setEqName(String eqName) {
    this.eqName = eqName;
  }

  public String getEqDescription() {
    return eqDescription;
  }

  public void setEqDescription(String eqDescription) {
    this.eqDescription = eqDescription;
  }

  public int getEqAmount() {
    return eqAmount;
  }

  public void setEqAmount(int eqAmount) {
    this.eqAmount = eqAmount;
  }

  @Override
  public String toString() {
    return "EquipmentInfo{" +
        "ID_equipment=" + ID_equipment +
        ", eqName='" + eqName + '\'' +
        ", eqDescription='" + eqDescription + '\'' +
        ", eqAmount=" + eqAmount +
        '}';
  }
}
