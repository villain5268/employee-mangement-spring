package com.boldnest.em.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boldnest.em.entity.Attendance;
import com.boldnest.em.repository.AttendanceRepository;
import com.boldnest.em.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Override
	public List<Attendance> getAttendanceByEmployeeId(Long employeeId) {
		return attendanceRepository.findByEmployeeId(employeeId);
	}

	@Override
	public void saveAttendance(Attendance attendance) {
		attendanceRepository.save(attendance);
	}

	@Override
	public List<Attendance> getAllAttendanceRecords() {
		return attendanceRepository.findAll();
	}

}
