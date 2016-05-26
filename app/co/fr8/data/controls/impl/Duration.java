package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;

import java.time.temporal.ChronoUnit;


/**
 * DTO Implementation for the Duration UI Control
 */
public class Duration extends ControlDefinitionDTO {

  private String innerlabel;
  private int days;
  private int hours;
  private int minutes;

  public Duration() {
    super(ControlTypeEnum.DURATION);
    this.innerlabel = "Choose Duration";
  }

  public java.time.Duration value() {
    long minutes = this.minutes + (this.hours * 60) + (this.days * 24 * 60);
//    java.time.Duration duration = java.time.Duration.ofMinutes(this.minutes);
//    duration.plusHours(this.hours);
//    duration.plusDays(this.days);
//    return duration;
    return java.time.Duration.ofMinutes(minutes);
  }

  public String getInnerlabel() {
    return innerlabel;
  }

  public void setInnerlabel(String innerlabel) {
    this.innerlabel = innerlabel;
  }

  public int getDays() {
    return days;
  }

  public void setDays(int days) {
    this.days = days;
  }

  public int getHours() {
    return hours;
  }

  public void setHours(int hours) {
    this.hours = hours;
  }

  public int getMinutes() {
    return minutes;
  }

  public void setMinutes(int minutes) {
    this.minutes = minutes;
  }
}
