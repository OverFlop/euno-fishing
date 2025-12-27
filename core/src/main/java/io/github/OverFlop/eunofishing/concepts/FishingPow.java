package concepts;

public class FishingPow {
  int power;

  public FishingPow() {
    this.power = 0;
  }

  public int getPower() {
    return power;
  }

  public void setPower(int power) {
    this.power = power;
  }

  public void addPowers(FishingPow newPow) {
    power += newPow.getPower();
  }

}
