package org.usfirst.frc.team4183.robot.subsystems.ElevatorSubsystem;

import org.usfirst.frc.team4183.robot.Robot;
import org.usfirst.frc.team4183.utils.CommandUtils;

import edu.wpi.first.wpilibj.command.Command;

public class Idle extends Command{

	public Idle()
	{
		requires(Robot.elevatorSubsystem);
		setRunWhenDisabled(true);
	}
	
	protected void initialize()
	{
		Robot.elevatorSubsystem.disable();
		Robot.oi.sbtnOpenMandible.release();
		//Robot.elevatorSubsystem.holdEncodPos(false);
		//Robot.elevatorSubsystem.holdEncodPos(true);
	}
	
	public void execute()
	{
		
	}
	
	protected boolean isFinished()
	{
		/// Temporary code for testing linkage
		/// TODO: magic 0.06
		if (Math.abs(Robot.oi.rightRampAxis.get()) > .06)		/// TODO: Need comment here explaining that sharing joytick!
		{														/// TODO: May want two instances with different names and put comment at definition
			Robot.oi.sbtnOpenMandible.push();
			return CommandUtils.stateChange(this, new Reposition());
		}
		return false;
	}

	protected void end()
	{
		
	}
	
	protected void interrupted()
	{
		end();
	}
	
}
