package com.getloc.syntech.getloc.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getloc.syntech.getloc.admin.User;
import com.getloc.syntech.getloc.admin.UserRepository;
import com.getloc.syntech.getloc.exceptions.AlreadyExistsException;
import com.getloc.syntech.getloc.exceptions.NotFoundException;
import com.getloc.syntech.getloc.mobile.Device;
import com.getloc.syntech.getloc.mobile.DeviceLocations;
import com.getloc.syntech.getloc.mobile.DeviceLocationsRepository;
import com.getloc.syntech.getloc.mobile.DeviceRepository;
import com.getloc.syntech.getloc.requests.devices.NewDevice;
import com.getloc.syntech.getloc.requests.devices.NewPositionData;
import com.getloc.syntech.getloc.requests.devices.UpdateDeviceName;
import com.getloc.syntech.getloc.responsesDTO.devicesDTO.NewPosition;
import com.getloc.syntech.getloc.responsesDTO.devicesDTO.RegisterDeviceDTO;

@Service
public class DeviceService {
    
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceLocationsRepository locationsRepository;

    private UserRepository userRepository;

    public RegisterDeviceDTO postDevice(String userId, NewDevice newDeviceData) {

        Optional<Device> device = deviceRepository.findByDeviceName(newDeviceData.devicename());

        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()) throw new NotFoundException("Usuário não existe!!!");
        if(device.isPresent()) throw new AlreadyExistsException("Já existe um dispositivo cadastrado com esse nome");

        Device newDevice = new Device(newDeviceData.devicename(), newDeviceData.location(), newDeviceData.longitudeAndLatitude());

        deviceRepository.save(newDevice);

        DeviceLocations deviceLocations = new DeviceLocations(newDevice.getDeviceId(), newDevice.getDeviceName(), newDevice.getLongitudeAndLatitude(), LocalDateTime.now());

        locationsRepository.save(deviceLocations);

        User updateUser = user.get();

        List<Device> devices = updateUser.getDevices();

        devices.add(newDevice);

        updateUser.setDevices(devices);

        userRepository.save(updateUser);

        RegisterDeviceDTO newDeviceDTO = new RegisterDeviceDTO(newDevice.getDeviceId(), newDevice.getDeviceName());

        return newDeviceDTO;
    }

    public String updateDeviceName(String id, UpdateDeviceName newDeviceNameData) {
        Optional<Device> device = deviceRepository.findById(id);
        if(device.isEmpty()) throw new NotFoundException("Dispositivo não encontrado!!!");        

        Device updatedDevice = device.get();
        
        updatedDevice.setDeviceName(newDeviceNameData.newname());

        deviceRepository.save(updatedDevice);

        return "Sucesso!";
    }

    public NewPosition postNewPosition(String userId, NewPositionData newPositionData) {
        Optional<Device> device = deviceRepository.findById(userId);

        if(device.isEmpty()) throw new NotFoundException("Dispositivo não encontrado!!!");

        Device updateDevice = device.get();

        updateDevice.setLocation(newPositionData.location());
        updateDevice.setLongitudeAndLatitude(newPositionData.longitudeAndLatitude());

        deviceRepository.save(updateDevice);
    }
}
