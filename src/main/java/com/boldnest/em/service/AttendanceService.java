package com.boldnest.em.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boldnest.em.entity.Attendance;

@Service
public interface AttendanceService {

	List<Attendance> getAttendanceByEmployeeId(Long employeeId);

	void saveAttendance(Attendance attendance);
	
	public List<Attendance> getAllAttendanceRecords();

}
