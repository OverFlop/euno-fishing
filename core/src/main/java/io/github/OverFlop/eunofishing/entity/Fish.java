package io.github.OverFlop.eunofishing.entity;

import com.badlogic.gdx.graphics.Texture;

import io.github.OverFlop.eunofishing.concepts.DifficultyPattern;
import io.github.OverFlop.eunofishing.concepts.Rarity;

/**
 * {@summary Fish class that creates a fish of a rarity, difficulty of capturing it and the pattern
 * the fish follows when being fished }
 */
public class Fish {
  private String name;
  private DifficultyPattern difficultyPattern;
  private int difficulty;
  private Rarity rarity;

  private Texture fishTexture;


  /**
   * @param name - Name of the fish
   * @param difficultyPattern - The movement pattern the fish has
   * @param difficulty - The difficulty of catching the fish
   * @param fishImg - Name of the fish image
   */
  public Fish(String name, DifficultyPattern difficultyPattern, int difficulty, Texture fishImg) {
    this.name = name;
    this.difficultyPattern = difficultyPattern;
    this.difficulty = difficulty;
    this.fishTexture = fishImg;

    if (this.difficulty <= 5) {
      this.rarity = Rarity.COMMON;

    } else if (this.difficulty <= 10 && this.difficulty > 5) {
      this.rarity = Rarity.UNCOMMON;

    } else if (this.difficulty <= 25 && this.difficulty > 10) {
      this.rarity = Rarity.RARE;

    } else if (this.difficulty <= 50 && this.difficulty > 25) {
      this.rarity = Rarity.UNIQUE;

    } else if (this.difficulty <= 75 && this.difficulty > 50) {
      this.rarity = Rarity.LEGENDARY;

    } else if (this.difficulty > 75) {
      this.rarity = Rarity.UNREAL;
    }
  }

  /**
   * @return this.name
   */
  public String getName() {
    return this.name;
  }

  /**
   * @return this.difficultyPattern
   */
  public DifficultyPattern getDifficultyPattern() {
    return this.difficultyPattern;
  }

  /**
   * @return this.difficulty
   */
  public int getDifficulty() {
    return this.difficulty;
  }

  /**
   * @return this.rarity
   */
  public Rarity getRarity() {
    return this.rarity;
  }

  public Texture getFishTexture() {
    return fishTexture;
  }

  public void setFishTexture(Texture fishTexture) {
    this.fishTexture = fishTexture;
  }
}


