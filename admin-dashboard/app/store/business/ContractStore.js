Ext.define('Admin.store.business.ContractStore', {
    extend: 'Ext.data.Store',
    alias: 'store.contractStore',			  //1.Store取别名（reference）
	model: 'Admin.model.business.ContractModel',
	proxy: {
		type: 'ajax',
		url: 'contract/findPage.json',
		reader: {
			type:'json', 
			rootProperty: 'content',		//结果集名字的属性
			totalProperty: 'totalElements'	
		},
		simpleSortMode: true
	},

	pageSize: 25,
	autoLoad: true,
	remoteSort: true,//全局排序
    sorters: {
        direction: 'DESC',
        property: 'contractId'
    }
});