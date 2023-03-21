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
    @Query("select me from DesignatedBloodWrite me where me.requestHospitalAddress like %:requestHospitalAddress%")
    List<DesignatedBloodWrite> filterArea(@Param("requestHospitalAddress") String requestHospitalAddress);

    @Query("select me from DesignatedBloodWrite me where me.patientIsRH like %:patientIsRH%")
    List<DesignatedBloodWrite> filterIsRH(@Param("patientIsRH") String patientIsRH);

    @Query("select me from DesignatedBloodWrite me where me.title like %:title%")
    List<DesignatedBloodWrite> filterTitle(@Param("title") String title);

    @Query("select me from DesignatedBloodWrite me where me.patientBlood like %:patientBlood%")
    List<DesignatedBloodWrite> filterPatientBlood(@Param("patientBlood") String patientBlood);

    @Query("select me from DesignatedBloodWrite me where me.content like %:content%")
    List<DesignatedBloodWrite> filterContent(@Param("content") String content);

    @Query("select me from DesignatedBloodWrite me where me.startDate like %:startDate%")
    List<DesignatedBloodWrite> filterStartDate(@Param("startDate") String startDate);


    //2가지 경우의 수
    @Query("select me from DesignatedBloodWrite me where me.patientIsRH like %:patientIsRH% and me.requestHospitalAddress like %:requestHospitalAddress%")
    List<DesignatedBloodWrite> IsRHAndArea(@Param("patientIsRH") String patientIsRH, @Param("requestHospitalAddress") String requestHospitalAddress);


    //3가지 경우의 수
    @Query("select me from DesignatedBloodWrite me where me.patientIsRH like %:patientIsRH% and me.requestHospitalAddress like %:requestHospitalAddress% and me.title like %:title%")
    List<DesignatedBloodWrite> IsRHAndAreaAndTitle(@Param("patientIsRH") String patientIsRH, @Param("requestHospitalAddress") String requestHospitalAddress, @Param("title") String title);


    //모든 값이 다 null일때 전체 게시물 리스트 출력
//    @Query("select me from DesignatedBloodWrite me where me.patientIsRH like %:patientIsRH% and me.requestHospitalAddress like %:requestHospitalAddress%")
//    List<DesignatedBloodWrite> IsRHAndArea(@Param("patientIsRH") String patientIsRH, @Param("requestHospitalAddress") String requestHospitalAddress);
}
