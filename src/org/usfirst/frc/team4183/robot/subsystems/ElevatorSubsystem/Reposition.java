package org.usfirst.frc.team4183.robot.subsystems.ElevatorSubsystem;

import org.usfirst.frc.team4183.robot.Robot;
import org.usfirst.frc.team4183.robot.RobotMap;
import org.usfirst.frc.team4183.utils.CommandUtils;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Reposition extends Command{
	
	private double initTime;
	
	// Seconds to wait for pneumatics to open
	private final double TIME_FOR_PNEUMATICS = 0.5; 

	public Reposition()
	{
		requires(Robot.elevatorSubsystem);	
	}
	
	public void init()
	{
		Robot.elevatorSubsystem.releasePos();
		initTime = timeSinceInitialized();
		
	}
	
	public void execute()
	{
		
		if(timeSinceInitialized() - initTime > TIME_FOR_PNEUMATICS) {
			Robot.oi.sbtnOpenMandible.release();
			double currPos = Robot.elevatorSubsystem.getElevatorNativeUnits();
			Robot.elevatorSubsystem.setSystemPower((currPos > RobotMap.ELEVATOR_SAFE_ZONE) 
													? Robot.oi.rightRampAxis.get() 
													: RobotMap.signedSqrt(Robot.oi.rightRampAxis.get()));
		}
		//Robot.elevatorSubsystem.setSystemPower(Robot.oi.leftRampAxis.get());
		
		/*
		if (Robot.oi.btnHighPosElev.get())
		{
			Robot.elevatorSubsystem.setElevPos(ElevatorSubsystem.ElevatorPositions.SCALE);
		}
		else if (Robot.oi.btnMedPosElev.get())
		{
			Robot.elevatorSubsystem.setElevPos(ElevatorSubsystem.ElevatorPositions.SWITCH);
		}
		else if (Robot.oi.btnLowPosElev.get())
		{
			Robot.elevatorSubsystem.setElevPos(ElevatorSubsystem.ElevatorPositions.SCALE);
		}
		else if (Robot.oi.btnTransPosElev.get())
		{
			Robot.elevatorSubsystem.setElevPos(ElevatorSubsystem.ElevatorPositions.TRANS);
		}
			
		if  (!Robot.elevatorSubsystem.posGreaterThanMin())
		{
			Robot.oi.sbtnOpenMandible.push();
		}
		else if (Robot.elevatorSubsystem.posCloseToInit())
		{
			Robot.oi.sbtnCloseMandible.push();
		}

		if (Math.abs(Robot.oi.leftRampAxis.get()) > .06)
		{
			Robot.elevatorSubsystem.setElevPos(ElevatorSubsystem.ElevatorPositions.MANUAL);
		}
		
		//Checks to see if the current ElevPos state is not in manual mode and sees if the elevator is not closeToItsDesired Position
		if (Robot.elevatorSubsystem.getElevPos() != ElevatorSubsystem.ElevatorPositions.MANUAL && !Robot.elevatorSubsystem.closeToDesiredPos())
		{
			Robot.elevatorSubsystem.goToPosition(Robot.elevatorSubsystem.getElevPos().getUnits());
			if (Robot.elevatorSubsystem.getElevPos().getUnits() > ElevatorSubsystem.ElevatorPositions.INIT.getUnits())
			{
				Robot.elevatorSubsystem.intakeThroat();
			}
			else
			{
				Robot.elevatorSubsystem.disableThroat();
			}
		}
		else
		{
			Robot.elevatorSubsystem.addToPosition(Robot.oi.leftRampAxis.get());
			if (Robot.elevatorSubsystem.getElevPos().getUnits() > ElevatorSubsystem.ElevatorPositions.INIT.getUnits())
			{
				Robot.elevatorSubsystem.intakeThroat();
			}
			else
			{
				Robot.elevatorSubsystem.disableThroat();
			}
		}
		*/
		
	}

	@Override
	protected boolean isFinished() {
		
		//Basically checks to see if the there is not any joystick movement or any buttons pressed for the elevPositions
		 
		/*if (Robot.oi.btnIdle.get() || Math.abs(Robot.oi.leftRampAxis.get()) < .06 || !Robot.oi.btnMedPosElev.get() 
				 || !Robot.oi.btnHighPosElev.get() || !Robot.oi.btnLowPosElev.get() || !Robot.oi.btnTransPosElev.get() 
				 || Robot.elevatorSubsystem.closeToDesiredPos())
			{
				Robot.elevatorSubsystem.holdPos();
				return CommandUtils.stateChange(this, new Idle());
			}
			*/
		if (Math.abs(Robot.oi.rightRampAxis.get()) < .06 || Robot.oi.btnIdle.get())
		{
			return CommandUtils.stateChange(this, new Idle());
		}
	return false;
}
}
