import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import javax.inject.Inject;

import com.api.j2ee.shoppings.repository.StoreRepository;
import com.api.j2ee.shoppings.repository.SegmentRepository;
import com.api.j2ee.shoppings.rest.StoreRest;
import com.api.j2ee.shoppings.entity.Store;
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
public class StoreRestTest {
	
	@Mock
	StoreRepository storeRepository;

	@Mock
	SegmentRepository segmentRepository;

	@InjectMocks
	StoreRest storeRest;

	@BeforeEach
    void init() {

    	lenient().when(storeRepository.findAll()).thenReturn(this.getListStoreMock());

    	lenient().when(storeRepository.findById(1L)).thenReturn(this.getListStoreMock().get(0));

    	lenient().when(segmentRepository.findById(1L)).thenReturn(this.getSegmentMock());
    }

    private Segment getSegmentMock(){
    	
    	Segment segment = new Segment();
    	segment.setId(1L);
    	segment.setName("Surfwear");
    	segment.setCreated(new java.util.Date());

    	return segment;
    }

    private List<Store> getListStoreMock(){

    	List<Store> listStore = new ArrayList<>();
    	
        Store store1 = new Store();
      	store1.setId(1L);
        store1.setName("Loja Modelo | Com Segmento 1");
        store1.setCreated(new Date());
        store1.setCnpj("86.140.865/0001-79");
        store1.setNumber(1);
        store1.setFloor(2);

        Store store2 = new Store();
        store2.setId(2L);
        store2.setName("Loja Modelo | Com Segmento 2");
        store2.setCreated(new Date());
        store2.setCnpj("99.667.667/0001-87");
        store2.setNumber(3);
        store2.setFloor(5);
    	
    	listStore.add(store1);
    	listStore.add(store2);

    	return listStore;
    }

    @Test
    public void testPopulateStoreWasCreated() {

    	Store store = storeRest.createPopulate();

    	verify(storeRepository, times(1)).create(any(Store.class));
    }

    @Test
    public void testStoreWasCreated(){

    	Store storeToCreate = getListStoreMock().get(0);

    	Store store = storeRest.createStore(storeToCreate);

    	verify(storeRepository, times(1)).create(any(Store.class));

    	assertEquals(storeToCreate.getName(), store.getName());

    }

    @Test
    public void testStoreFindById(){

    	Store store = storeRest.getStoreById(1L);

    	verify(storeRepository, times(1)).findById(any(Long.class));

    }

    @Test
    public void testStoreUpdate(){

    	Store storeToUpdate = getListStoreMock().get(0);

    	Store store = storeRest.updateStore(storeToUpdate);

    	verify(storeRepository, times(1)).findById(storeToUpdate.getId());

    	verify(storeRepository, times(1)).update(any(Store.class));

    }

    @Test
    public void testListAllStores(){

    	List<Store> listStore = storeRest.getStoreList();

    	final Integer EXPECTED_SEGMENTS_COUNT = 2;

    	verify(storeRepository, times(1)).findAll();

    	assertEquals(new Integer(listStore.size()), EXPECTED_SEGMENTS_COUNT);

    }
}
