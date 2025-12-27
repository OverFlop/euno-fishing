package Entity;

import Concepts.DifficultyPattern;
import Concepts.Rarity;

/**
 * {@summary}
 */
public class Fish {
  private String name;
  private DifficultyPattern difficultyPattern;
  private int difficulty;

  private int exp;
  private Rarity rarity;

  /**
   * {@summary}
   * 
   * @param name
   * @param pattern
   * @param difficulty
   */
  public Fish(String name, DifficultyPattern difficultyPattern, int difficulty) {
    this.name = name;
    this.difficultyPattern = difficultyPattern;
    this.difficulty = difficulty;

    if (this.difficulty <= 2) {
      this.rarity = Rarity.COMMON;
      this.exp = 5;

    } else if (this.difficulty <= 5 && this.difficulty > 2) {
      this.rarity = Rarity.UNCOMMON;
      this.exp = 10;

    } else if (this.difficulty <= 10 && this.difficulty > 5) {
      this.rarity = Rarity.RARE;
      this.exp = 20;

    } else if (this.difficulty <= 10 && this.difficulty > 6) {
      this.rarity = Rarity.UNIQUE;
      this.exp = 30;

    } else if (this.difficulty <= 30 && this.difficulty > 8) {
      this.rarity = Rarity.LEGENDARY;
      this.exp = 60;

    } else if (this.difficulty > 30) {
      this.rarity = Rarity.UNREAL;
      this.exp = 100;
    }
  }

  /**
   * @return
   */
  public String getName() {
    return this.name;
  }

  /**
   * @return
   */
  public DifficultyPattern getDifficultyPattern() {
    return this.difficultyPattern;
  }

  /**
   * @return
   */
  public int getDifficulty() {
    return this.difficulty;
  }

  /**
   * @return
   */
  public int getExp() {
    return this.exp;
  }

  /**
   * @return
   */
  public Rarity getRarity() {
    return this.rarity;
  }
}


