package put.io.patterns.implement;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

public class SystemMonitor {

    private SystemInfo si;

    private HardwareAbstractionLayer hal;

    private OperatingSystem os;

    private SystemState lastSystemState = null;

    private List<SystemStateObserver> observers = new ArrayList<SystemStateObserver>();

    public SystemMonitor(){
        si = new SystemInfo();
        hal = si.getHardware();
        os = si.getOperatingSystem();

    }

    public SystemMonitor() {
        this.hal = this.si.getHardware();
        this.os = this.si.getOperatingSystem();
    }
	
	public void addSystemStateObserver(SystemStateObserver observer){
        observers.add(observer);
    }
    public void removeSystemStateObserver(SystemStateObserver observer){
        observers.remove(observer);
    }
	
	public void notifyObservers(){
		for(SystemStateObserver observer : this.observers){
			observer.update(this);
		}
	}

    public interface SystemStateObserver{
		public void update(SystemMonitor monitor);
    }

    public void probe(){

        // Get current state of the system resources
        double cpuLoad = hal.getProcessor().getSystemLoadAverage(1)[0];
        double cpuTemp = hal.getSensors().getCpuTemperature();
        double memory = hal.getMemory().getAvailable() / 1000000;
        int usbDevices = hal.getUsbDevices(false).size();

        lastSystemState = new SystemState(cpuLoad, cpuTemp, memory, usbDevices);

        // Print information to the console
        System.out.println("============================================");
        System.out.println(String.format("CPU Load: %2.2f%%", lastSystemState.getCpu()));
        System.out.println(String.format("CPU temperature: %.2f C", lastSystemState.getCpuTemp()));
        System.out.println(String.format("Available memory: %.2f MB", lastSystemState.getAvailableMemory()));
        System.out.println(String.format("USB devices: %d", lastSystemState.getUsbDevices()));

        // Run garbage collector when out of memory
        if (lastSystemState.getAvailableMemory() < 200.00){
            System.out.println("> Running garbage collector...");
        }

        // Increase CPU cooling if the temperature is to high
        if (lastSystemState.getCpuTemp() > 60.00){
            System.out.println("> Increasing cooling of the CPU...");
        }

        notifyObservers();
    }

    public SystemState getLastSystemState() {
        return lastSystemState;
    }

    public class SystemGarbageCollectorObserver implements SystemMonitor.SystemStateObserver {
        @Override
        public void update(SystemMonitor monitor) {
            if (monitor.getLastSystemState().getAvailableMemory() < 200.0) {
                System.out.println("Running garbage collector...");
            }
        }
	}
	
	public class SystemCoolerObserver implements SystemMonitor.SystemStateObserver {
		@Override
		public void update(SystemMonitor monitor) {
			SystemState state = monitor.getLastSystemState();
			if (state.getCpuTemp() > 60.0) {
				System.out.println("> Increasing cooling of the CPU...");
			}
		}
	}
	
	public class USBDeviceObserver implements SystemMonitor.SystemStateObserver {
		private int lastUsbDevices = 0;

		@Override
		public void update(SystemMonitor monitor) {
			SystemState state = monitor.getLastSystemState();
			if (state.getUsbDevices() != lastUsbDevices) {
				System.out.println("> USB devices count changed: " + state.getUsbDevices());
				lastUsbDevices = state.getUsbDevices();
			}
		}
	}
}
