/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class EncoderSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static DifferentialDrive drive;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
   // setDefaultCommand(new ElevatorPostion());
  }
  public void initElevator(){
    WPI_TalonSRX leftMotors = new WPI_TalonSRX(2);
    WPI_TalonSRX rightMotors = new WPI_TalonSRX(3);
    drive = new DifferentialDrive(leftMotors, rightMotors);
    RobotMap.leftEncoder.setDistancePerPulse(1./256.);
    RobotMap.rightEncoder.setDistancePerPulse(1./256.);
    RobotMap.rightEncoder.reset();
    RobotMap.leftEncoder.reset();
  }
  public void motorSet() {
    double error = RobotMap.leftEncoder.getDistance() - RobotMap.rightEncoder.getDistance();

    // Drives forward continuously at half speed, using the encoders to stabilize the heading
   System.out.println(RobotMap.leftEncoder.getDistance());
   System.out.println(RobotMap.rightEncoder.getDistance());

    if(RobotMap.leftEncoder.getDistance() < 100 && RobotMap.rightEncoder.getDistance() < 100 ) {
       drive.tankDrive(.5 + 1 * error, .5 - 1 * error);
   } else {
       drive.tankDrive(0, 0);
   }
 
  }
  public void stop() {
   drive.tankDrive(0, 0);
  }
}
