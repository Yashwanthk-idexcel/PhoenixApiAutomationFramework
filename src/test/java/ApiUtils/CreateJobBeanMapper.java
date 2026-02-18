package ApiUtils;

import java.util.ArrayList;
import java.util.List;

import DataProvidersApiBeans.CreateJobBean;
import RequestModel.CreateJobPayload;
import RequestModel.Customer;
import RequestModel.CustomerAddress;
import RequestModel.CustomerProduct;
import RequestModel.Problems;

public class CreateJobBeanMapper {

	private CreateJobBeanMapper() {

	}

	public static CreateJobPayload mapper(CreateJobBean bean) {
		// Bean -> CreateJobPayload Object

		int mstServiceLocationId = Integer.parseInt(bean.getMst_service_location_id());
		int mstPlatformId = Integer.parseInt(bean.getMst_platform_id());
		int mstOemId = Integer.parseInt(bean.getMst_oem_id());
		int mstWarrantyStatusId = Integer.parseInt(bean.getMst_warrenty_status_id());

		Customer customer = new Customer(bean.getCustomer__first_name(), bean.getCustomer__last_name(),
				bean.getCustomer__mobile_number(), bean.getCustomer__mobile_number_alt(), bean.getCustomer__email_id(),
				bean.getCustomer__email_id_alt());

		CustomerAddress customerAddress = new CustomerAddress(bean.getCustomer_address__flat_number(),
				bean.getCustomer_address__apartment_name(), bean.getCustomer_address__street_name(),
				bean.getCustomer_address__landmark(), bean.getCustomer_address__area(),
				bean.getCustomer_address__pincode(), bean.getCustomer_address__country(),
				bean.getCustomer_address__state());

		int productId = Integer.parseInt(bean.getCustomer_product__product_id());
		int modelId = Integer.parseInt(bean.getCustomer_product__mst_model_id());

		CustomerProduct customerProduct = new CustomerProduct(bean.getCustomer_product__dop(),
				bean.getCustomer_product__serial_number(), bean.getCustomer_product__imei1(),
				bean.getCustomer_product__imei2(), bean.getCustomer_product__popurl(), productId, modelId);

		List<Problems> problemList = new ArrayList<Problems>();
		int problemModelId = Integer.parseInt(bean.getProblems__id());
		Problems problem = new Problems(problemModelId, bean.getProblems__remark());
		problemList.add(problem);

		CreateJobPayload payload = new CreateJobPayload(mstServiceLocationId, mstPlatformId, mstWarrantyStatusId,
				mstOemId, customer, customerAddress, customerProduct, problemList);
		
		return payload;
	}
}
