package project.bcvita.user.repository.designatedBlood;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.designatedBlood.DesignatedBloodWrite;

import java.util.List;
import java.util.Optional;

@Repository
public interface DesignatedBloodWriteRepository extends JpaRepository<DesignatedBloodWrite, Long> {

    Optional<DesignatedBloodWrite> findDesignatedBloodWriteById(Long id);


    DesignatedBloodWrite findById(String designatedId);

    //List<DesignatedBloodWrite> findById(String id);


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

    @Query("select me from DesignatedBloodWrite me where me.hospitalName like %:hospitalName%")
    List<DesignatedBloodWrite> filterHospitalName(@Param("hospitalName") String hospitalName);

    @Query("select me from DesignatedBloodWrite me where me.bloodType like %:bloodType%")
    List<DesignatedBloodWrite> filterBloodType(@Param("bloodType") String bloodType);


    //2가지 경우의 수
    @Query("select me from DesignatedBloodWrite me where me.patientIsRH like %:patientIsRH% and me.requestHospitalAddress like %:requestHospitalAddress%")
    List<DesignatedBloodWrite> IsRHAndArea(@Param("patientIsRH") String patientIsRH, @Param("requestHospitalAddress") String requestHospitalAddress);

    @Query("select me from DesignatedBloodWrite me where me.patientBlood like %:patientBlood% and me.requestHospitalAddress like %:requestHospitalAddress%")
    List<DesignatedBloodWrite> filterBloodAndAddress(@Param("patientBlood") String patientBlood, @Param("requestHospitalAddress") String requestHospitalAddress);

    @Query("select me from DesignatedBloodWrite me where me.title like %:title% and me.content like %:content%")
    List<DesignatedBloodWrite> filterTitleAndContent(@Param("title") String title, @Param("content") String content);

    @Query("select me from DesignatedBloodWrite me where me.title like %:title% and me.requestHospitalAddress like %:requestHospitalAddress%")
    List<DesignatedBloodWrite> filterTitleAndAddress(@Param("title") String title, @Param("requestHospitalAddress") String requestHospitalAddress);

    @Query("select me from DesignatedBloodWrite me where me.patientIsRH like %:patientIsRH% and me.bloodType like %:bloodType%")
    List<DesignatedBloodWrite> filterRhAndType(@Param("patientIsRH") String patientIsRH, @Param("bloodType") String bloodType);

    @Query("select me from DesignatedBloodWrite me where me.hospitalName like %:hospitalName% and me.title like %:title%")
    List<DesignatedBloodWrite> filterHospitalNameAndTitle(@Param("hospitalName") String hospitalName, @Param("title") String title);

    @Query("select me from DesignatedBloodWrite me where me.hospitalName like %:hospitalName% and me.content like %:content%")
    List<DesignatedBloodWrite> filterHospitalNameAndContent(@Param("hospitalName") String hospitalName, @Param("content") String content);

    @Query("select me from DesignatedBloodWrite me where me.hospitalName like %:hospitalName% and me.patientBlood like %:patientBlood%")
    List<DesignatedBloodWrite> filterHospitalNameAndPatientBlood(@Param("hospitalName") String hospitalName, @Param("patientBlood") String patientBlood);

    @Query("select me from DesignatedBloodWrite me where me.hospitalName like %:hospitalName% and me.bloodType like %:bloodType%")
    List<DesignatedBloodWrite> filterHospitalNameAndBloodType(@Param("hospitalName") String hospitalName, @Param("bloodType") String bloodType);

    @Query("select me from DesignatedBloodWrite me where me.hospitalName like %:hospitalName% and me.patientIsRH like %:patientIsRH%")
    List<DesignatedBloodWrite> filterHospitalNameAndRh(@Param("hospitalName") String hospitalName, @Param("patientIsRH") String patientIsRH);


    //3가지 경우의 수
    @Query("select me from DesignatedBloodWrite me where me.patientIsRH like %:patientIsRH% and me.requestHospitalAddress like %:requestHospitalAddress% and me.title like %:title%")
    List<DesignatedBloodWrite> IsRHAndAreaAndTitle(@Param("patientIsRH") String patientIsRH, @Param("requestHospitalAddress") String requestHospitalAddress, @Param("title") String title);


    @Query("select me from DesignatedBloodWrite me where me.patientIsRH like %:patientIsRH% and me.content like %:content% and me.title like %:title%")
    List<DesignatedBloodWrite> filterRhAndContentAndTitle(@Param("patientIsRH") String patientIsRH, @Param("content") String content, @Param("title") String title);
}

