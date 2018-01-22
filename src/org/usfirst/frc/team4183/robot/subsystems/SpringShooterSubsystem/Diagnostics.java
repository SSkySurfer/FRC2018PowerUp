package org.usfirst.frc.team4183.robot.subsystems.SpringShooterSubsystem;

import org.usfirst.frc.team4183.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Diagnostics extends Command {

    public Diagnostics() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    		requires(Robot.springShooterSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(diagInitLoops < Robot.springShooterSubsystem.DIAG_LOOPS_RUN) {
    		Robot.springShooterSubsystem.diagnosticsInit();
    		diagInitLoops++;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {

    	if(diagInitLoops >= Robot.springShooterSubsystem.DIAG_LOOPS_RUN) {
    		Robot.springShooterSubsystem.diagnosticsCheck();
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
