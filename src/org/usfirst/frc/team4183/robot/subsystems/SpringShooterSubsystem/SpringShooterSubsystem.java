package org.usfirst.frc.team4183.robot.subsystems.SpringShooterSubsystem;
import org.usfirst.frc.team4183.robot.RobotMap;
import org.usfirst.frc.team4183.robot.subsystems.BitBucketsSubsystem;
import org.usfirst.frc.team4183.robot.subsystems.SpringShooterSubsystem.Idle;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
/**
 *
 */
public class SpringShooterSubsystem extends BitBucketsSubsystem 
{
	private final WPI_TalonSRX motorA;		// User follower mode
	private final WPI_TalonSRX motorB;
	
	private final DoubleSolenoid gearShifter;
	private boolean present = false;
	
	// Constructor
	public SpringShooterSubsystem()
	{
		motorA = new WPI_TalonSRX(RobotMap.SPRING_SHOOTER_MOTOR_A_ID);
		motorB = new WPI_TalonSRX(RobotMap.SPRING_SHOOTER_MOTOR_B_ID);
		
		// Attempt to remove the 100ms safety gate
    	motorA.setSafetyEnabled(false);
    	motorB.setSafetyEnabled(false);
    	
    	// Use follower mode to minimize shearing commands that could occur if
    	// separate commands are sent to each motor in a group
		// NOTE: Follower mode may not stay latched and should be pushed each cycle
		motorB.set(ControlMode.Follower, motorA.getDeviceID());
		
		gearShifter = new DoubleSolenoid(RobotMap.SPRING_SHOOTER_SHIFTER_HIGH_PNEUMA_CHANNEL, 
				                         RobotMap.SPRING_SHOOTER_SHIFTER_NEUTRAL_PNEUMA_CHANNEL);
		
		DIAG_LOOPS_RUN=20;
		
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Idle());
    }
    
	public void disable() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void diagnosticsInit() {
		// TODO Auto-generated method stub
		
		motorA.set(ControlMode.PercentOutput, RobotMap.MOTOR_TEST_PERCENT);//set one motor at time
		
	}
	@Override
	public void diagnosticsCheck() {
		// TODO Auto-generated method stub
		runDiagnostics=false;
		motorA.set(ControlMode.PercentOutput, 0);
		
	}

	@Override
	public void periodic() {
		// TODO Auto-generated method stub
		
	}

	public void setPresent(boolean b) {
		present = b;
		
	}

	public boolean isPresent() {
		return present;
	}

	@Override
	public void diagnosticsExecute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDiagnosticsFlag(boolean enable) {
		// TODO Auto-generated method stub
		runDiagnostics=enable;
	}

	@Override
	public boolean getDiagnosticsFlag() {
		// TODO Auto-generated method stub
		return runDiagnostics;
	}
}