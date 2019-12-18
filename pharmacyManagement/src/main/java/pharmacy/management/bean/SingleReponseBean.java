package pharmacy.management.bean;

import java.util.List;

import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;

public class SingleReponseBean {
	private List<TProduct> tProduct;
	private List<TProductGroup> tProductGroup;
	private List<CartBean> tOrder;

	public List<CartBean> gettOrder() {
		return tOrder;
	}

	public void settOrder(List<CartBean> tOrder) {
		this.tOrder = tOrder;
	}

	public List<TProductGroup> gettProductGroup() {
		return tProductGroup;
	}

	public void settProductGroup(List<TProductGroup> tProductGroup) {
		this.tProductGroup = tProductGroup;
	}

	public List<TProduct> gettProduct() {
		return tProduct;
	}

	public void settProduct(List<TProduct> tProduct) {
		this.tProduct = tProduct;
	}
	
}
