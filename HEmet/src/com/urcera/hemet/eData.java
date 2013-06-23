/*
 *  Copyright (C)2013, Jesus Urcera Lopez
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see http://www.gnu.org/licenses/. 
 *
 *  Author : Jesus Urcera Lopez <jurcera at gmail dot com>
 *
*/

package com.urcera.hemet;

public class eData {
	private long id;
	private String date;
	private double voltage;
	private double current;
	private double frequency;
	private double poweract;
	private double powerreact;
	private double powerapp;
	private double energyact;
	private double energyreact;
	private double energyapp;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public double getVoltage() {
		return voltage;
	}

	public void setVoltage(double voltage) {
		this.voltage = voltage;
	}
	
	public double getCurrent() {
		return current;
	}

	public void setCurrent(double current) {
		this.current = current;
	}
	
	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}
	
	public double getPoweract() {
		return poweract;
	}

	public void setPoweract(double poweract) {
		this.poweract = poweract;
	}
	
	public double getPowerreact() {
		return powerreact;
	}

	public void setPowerreact(double powerreact) {
		this.powerreact = powerreact;
	}
	
	public double getPowerapp() {
		return powerapp;
	}

	public void setPowerapp(double powerapp) {
		this.powerapp = powerapp;
	}
	
	public double getEnergyact() {
		return energyact;
	}

	public void setEnergyact(double energyact) {
		this.energyact = energyact;
	}
	
	public double getEnergyreact() {
		return energyreact;
	}

	public void setEnergyreact(double energyreact) {
		this.energyreact = energyreact;
	}
	
	public double getEnergyapp() {
		return energyapp;
	}

	public void setEnergyapp(double energyapp) {
		this.energyapp = energyapp;
	}
	
		
	@Override
	public String toString() {
		return date;
	}

}
