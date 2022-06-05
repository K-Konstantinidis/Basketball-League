package com.example.esake;

public enum PlayerPosition
{
	PointGuard,
	ShootingGuard,
	SmallForward,
	PowerForward,
	Center;
	
	@Override
	public String toString() {
		//TODO replace return values with strings.xml values!
		switch (this) {
			case PointGuard:
				return "Point Guard";
			case ShootingGuard:
				return "Shooting Guard";
			case SmallForward:
				return "Small Forward";
			case PowerForward:
				return "Power Forward";
			case Center:
				return "Center";
			default:
				return null;
		}
	}
}
