Ext.define('Admin.store.address.AddressStore', {
    extend: 'Ext.data.Store',
    alias: 'store.addressStore',			  //1.Store取别名（reference）
    data:{
		'items':[
			{ 'addressId': 1, 'addressName': 'Lisa',  "addressDepartment":"销售部","addressPhone":"13631784344",'addressEmail': '123456@qq.com','addressQQ': '9634789895'},
			{ 'addressId': 2, 'addressName': 'Lisa',  "addressDepartment":"网络部","addressPhone":"13631784344",'addressEmail': '123456@qq.com','addressQQ': '9634789895'},
			{ 'addressId': 3, 'addressName': 'Lisa',  "addressDepartment":"人事部", "addressPhone":"13631784344",'addressEmail': '123456@qq.com','addressQQ': '9634789895'},
			{ 'addressId': 4, 'addressName': 'Lisa', "addressDepartment":"财务部", "addressPhone":"13631784344",'addressEmail': '123456@qq.com','addressQQ': '9634789895'}
		]
	},
	proxy: {
		type: 'memory',
		reader: {
			type: 'json',
			root: 'items'
		}
	},

	pageSize: 25,
	autoLoad: true,
	remoteSort: true,//全局排序
    sorters: {
        direction: 'DESC',
        property: 'id'
    }
});