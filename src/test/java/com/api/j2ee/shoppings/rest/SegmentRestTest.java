import static org.junit.jupiter.api.Assertions.assertEquals;
import javax.inject.Inject;

import com.api.j2ee.shoppings.repository.SegmentRepository;
import com.api.j2ee.shoppings.rest.SegmentRest;
import com.api.j2ee.shoppings.entity.Segment;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class SegmentRestTest {

	@Mock
	SegmentRepository segmentRepository;

	@InjectMocks
	SegmentRest segmentRest;

	@BeforeEach
    void init() {

    	lenient().when(segmentRepository.findAll()).thenReturn(this.getListSegmentMock());

    }

    private List<Segment> getListSegmentMock(){

    	List<Segment> listSegments = new ArrayList<>();
    	
    	Segment segment1 = new Segment();
    	segment1.setId(1L);
    	segment1.setName("Surfwear");
    	segment1.setCreated(new java.util.Date());

    	Segment segment2 = new Segment();
    	segment2.setId(2L);
    	segment2.setName("Moda Feminina");
    	segment2.setCreated(new java.util.Date());
    	
    	listSegments.add(segment1);
    	listSegments.add(segment2);

    	return listSegments;
    }

    @Test
    public void testPopulateSegmentsHasFourItemsTest() {

    	List<Segment> segmentList = segmentRest.populateSegments();

    	final Integer EXPECTED_SEGMENTS_COUNT = 4; 

    	assertEquals(new Integer(segmentList.size()), EXPECTED_SEGMENTS_COUNT);
    }

    @Test
    public void testPopulateSegmentsIfFirstUserWasCreatedTest() {

    	List<Segment> segmentList = segmentRest.populateSegments();

    	final Integer EXPECTED_SEGMENTS_COUNT = 4; 

    	verify(segmentRepository).create(segmentList.get(0));
    }

    @Test
    public void testListAllSegments(){

    	List<Segment> segmentList = segmentRest.getSegmentList();

    	final Integer EXPECTED_SEGMENTS_COUNT = 2;

    	verify(segmentRepository, times(1)).findAll();

    	assertEquals(new Integer(segmentList.size()), EXPECTED_SEGMENTS_COUNT);

    }
}
