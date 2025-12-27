package concepts;

import entity.Fish;

/**
 * {@summary Experience class that stores the current player exp, exp threshold to next level and
 * level}
 */
public class Experience {
  private int experienceLevel;
  private int expToNextLevel;
  private int currentExp;

  public Experience() {
    this.experienceLevel = 1;
    this.expToNextLevel = 25;
    this.currentExp = 0;
  }

  /**
   * @return this.experienceLevel
   */
  public int getExperienceLevel() {
    return this.experienceLevel;
  }

  /**
   * @return this.expToNextLevel
   */
  public int getExpToNextLevel() {
    return this.expToNextLevel;
  }


  /**
   * {@summary}
   * 
   * @param caughtFish - The fish caught by the player
   */
  public void addExp(Fish caughtFish) {
    switch (caughtFish.getRarity()) {
      case COMMON:
        this.currentExp += 5;
        break;

      case UNCOMMON:
        this.currentExp += 10;
        break;

      case RARE:
        this.currentExp += 25;
        break;

      case UNIQUE:
        this.currentExp += 50;
        break;

      case LEGENDARY:
        this.currentExp += 70;
        break;

      case UNREAL:
        this.currentExp += 150;
        break;

      default:
        break;
    }

  }

  /**
   * {@summary This levels up the user if the current exp has reached the threshold and sets an new
   * threshold.}
   */
  public void levelUp() {
    if (this.currentExp >= this.expToNextLevel) {
      this.currentExp = 0;
      this.expToNextLevel += Math.round(Math.pow(this.experienceLevel, 1.9) + 25);
      this.experienceLevel += 1;
    }
  }



}
