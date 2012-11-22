package com.sagaciouszed.bukkit;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class is a java bean.
 * Its purpose is to represent a row in a database. It must have a no args
 * constructor. All the fields have been annotated to express their properties
 * in the database.
 * 
 */
@Entity(name = "HOME")
@Table(name = "samplehome_home")
public class Home implements Serializable {

    @Id @Column(nullable = false) String name;

    // Data for a location

    @Column(nullable = false) String worldUuid;

    @Column(nullable = false) double locX;

    @Column(nullable = false) double locY;

    @Column(nullable = false) double locZ;

    @Column(nullable = false) float locPitch;

    @Column(nullable = false) float locYaw;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorldUuid() {
        return worldUuid;
    }

    public void setWorldUuid(String world_uuid) {
        this.worldUuid = world_uuid;
    }

    public double getLocX() {
        return locX;
    }

    public void setLocX(double locX) {
        this.locX = locX;
    }

    public double getLocY() {
        return locY;
    }

    public void setLocY(double locY) {
        this.locY = locY;
    }

    public double getLocZ() {
        return locZ;
    }

    public void setLocZ(double locZ) {
        this.locZ = locZ;
    }

    public float getLocPitch() {
        return locPitch;
    }

    public void setLocPitch(float locPitch) {
        this.locPitch = locPitch;
    }

    public float getLocYaw() {
        return locYaw;
    }

    public void setLocYaw(float locYaw) {
        this.locYaw = locYaw;
    }

}
