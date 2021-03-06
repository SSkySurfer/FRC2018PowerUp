package org.usfirst.frc.team4183.robot.subsystems.ElevatorSubsystem;

import org.usfirst.frc.team4183.robot.Robot;
import org.usfirst.frc.team4183.utils.CommandUtils;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Diagnostics extends Command {
	
	private int diagInitLoops;

    public Diagnostics() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevatorSubsystem);
    	diagInitLoops = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevatorSubsystem.diagnosticsInit();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(diagInitLoops < Robot.elevatorSubsystem.DIAG_LOOPS_RUN) {
    		Robot.elevatorSubsystem.diagnosticsExecute();
    		diagInitLoops++;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(diagInitLoops >= Robot.elevatorSubsystem.DIAG_LOOPS_RUN) {
    		Robot.elevatorSubsystem.diagnosticsCheck();
    		return CommandUtils.stateChange(this, new Idle());
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
