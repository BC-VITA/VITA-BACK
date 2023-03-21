package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.bcvita.user.dto.request.BoardCreateRequestDto;
import project.bcvita.user.entity.DesignatedBloodWrite;

import java.util.List;

@Repository
public interface DesignatedBloodWriteRepository extends JpaRepository<DesignatedBloodWrite, Long> {

    //1가지 경우의 수
    @Query("select me from DesignatedBloodWrite me where me.requestHospitalAddress like %:area%")
    List<DesignatedBloodWrite> filterArea(@Param("area") String area);

    @Query("select me from DesignatedBloodWrite me where me.patientIsRH like %:RH%")
    List<DesignatedBloodWrite> filterIsRH(@Param("RH") String RH);

//    @Query("select me from DesignatedBloodWrite me where me.title like %:title%")
//    List<DesignatedBloodWrite> filterTitle(@Param("title") String title);
//
//    @Query("select me from DesignatedBloodWrite me where me.patientBlood like %:PatientBlood%")
//    List<DesignatedBloodWrite> filterPatientBlood(@Param("PatientBlood") String PatientBlood);
//
//    @Query("select me from DesignatedBloodWrite me where me.content like %:content%")
//    List<DesignatedBloodWrite> filterContent(@Param("content") String content);

    //2가지 경우의 수
    @Query("select me from DesignatedBloodWrite me where me.patientIsRH like %:RH% and me.requestHospitalAddress like %:area%")
    List<DesignatedBloodWrite> IsRHAndArea(@Param("RH") String RH, @Param("area") String area);

}
