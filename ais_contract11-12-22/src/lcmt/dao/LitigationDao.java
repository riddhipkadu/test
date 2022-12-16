package lcmt.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import lcmt.domain.LitigationAdvocateFees;
import lcmt.domain.AdvocatePaidFees;
import lcmt.domain.CounselPaidFees;
import lcmt.domain.ExternalCounsel;
import lcmt.domain.HearingStage;
import lcmt.domain.HearingStageOnHearing;
import lcmt.domain.Litigation;
import lcmt.domain.LitigationCounselFees;
import lcmt.domain.LitigationEscalationMailId;
import lcmt.domain.LitigationType;

public interface LitigationDao {
	public void persist(Object object);
	public int isLitiNameExist(int liti_id, String liti_name);
	public <T> List<T> getAllLitiType(Class<T> clazz);
	public LitigationType getLiti_typeById(int liti_id);
	public void updateLitigation_type(LitigationType litigation);
	public int deleteLitigationType(int liti_id);
	public int checkDependancyLitigationType(int liti_id);
	public int persistLitigation(Litigation object);
	public <T> List<T> getAllLitigation(Class<T> clazz);
	public int getMaxLastGeneratedValueForClientLitiId();
	public <T> List<T>  getJoinedLitigationDetailsById(int liti_id);
	public int saveHearingStage(HearingStage hearingStage);
	public List<Object> getHearingDetailsByLitiId(int liti_id);
	public HearingStage getHearingDetailsByStageId(int hear_stage_id);
	public void updateHearingStage(HearingStage hearingStage);
	public Litigation getLitigationByLitiId(int liti_id);
	public void updateLitigation(Litigation litigation);
	public <T> List<T> searchLitigation(String json,HttpSession session);
	public String getLatestNextHearingStageDate(int liti_id);
	public int saveCounselFees(LitigationCounselFees counselFees);
	public <T> List<T> getCounselFeesDeatilsByLitiId(int liti_id);
	public List<Object> getMatterHandleByUsers();
	public List<ExternalCounsel> getConselAsPerLawFirm(int law_firm_id);
	public List<Object> getLawFirmExternalCounsel(int liti_id);
	public <T> List<T> getAdvocateFeesDeatilsByLitiId(int liti_id);
	public LitigationCounselFees getCounselFeesDetailsById(int lcou_id);
	public LitigationAdvocateFees getAdvocateFeesDetailsById(int ladv_id);
	public void merge(Object object);
	public <T> List<T> getAllLitigationAccessWise(Class<T> clazz,HttpSession session);
	public <T> List<T> getAllListLitigation();
	public List<Object> getAllListLitigationAccessWise(HttpSession session);
	public <T> List<T> getPaidFeesDetailsByConFeesId(int counsel_fees_id);
	public <T> List<T> getPaidFeesDetailsByAdvocateFeesId(int advocate_fees_id);
	public AdvocatePaidFees getAdvocatePaidFeesById(int apaid_id);
	public CounselPaidFees getCounselPaidFeesById(int cpaid_id);
	public <T> List<T> getHearingStageLogs(String json);
	public int deleteLitigation(int liti_id);
	public int isCounselPaidFeesGreaterThanAgreedFees(int cpaid_fees_amount, int cpaid_counsel_fees_id);
	public int isAdvocatePaidFeesGreaterThanAgreedFees(int apaid_fees_amount, int apaid_advocate_fees_id);
	public int deleteHearingStage(int hear_stage_id);
    public List<Object> getCounselByHearingStageId(int hearing_stage_id);
    public int deleteHearingCounselById(int hsco_id);
    public <T> List<T> getAllPendingLitigationClientId(Class<T> clazz);
    public String getpreviouslitiId(String liti_id);
    public void update(Object obj);
    public LitigationEscalationMailId getEscalationMailByLitiId(int liti_id);
    public int persistHearingStageOnHearing(HearingStage hearingStage);
    public List<Object> getHearingDetailsOnHearingDay(int liti_id);
    public HearingStageOnHearing getHearingOnHearingDetailsById(int hear_id);
    
}
