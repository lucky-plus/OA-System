Ext.define('Admin.store.log.LogStore', {
    extend: 'Ext.data.Store',
    alias: 'store.logStore',       //1.Store取别名（reference）
    model: 'Admin.model.log.LogModel',//2.设置model的全路径
  	proxy: {
		type: 'ajax',
		url: 'log/findAll.json',
		reader: {
			type:'json', 
			rootProperty: 'content',		//结果集名字的属性
			totalProperty: 'totalElements'	//一共多少条记录的属性
		},
		simpleSortMode: true
	},

	pageSize: 15,
	autoLoad: true,
	remoteSort: true,//全局排序
    sorters: {
        direction: 'DESC',
        property: 'createDate'
    }
});

//假数据测试
// Ext.define('Admin.store.role.RoleStore', {
//     extend: 'Ext.data.Store',
//     alias: 'store.roleStore',       //1.Store取别名（reference）
//     model: 'Admin.model.role.RoleModel',//2.设置model的全路径
//   proxy: {
//     //type: 'ajax',
//     type: 'memory',
//     //url: 'role/findPage.json',  //后台OrderController中的接口url地址
//     data:{
//     'items':[
//     { 'roleId': 1, 'roleName': 'Lisa',  "roleLevel":"1",  "modulesText":"555-111-1224"  }
//     ]},
//     reader: {
//       type:'json', 
//       //rootProperty: 'content',    //结果集名字的属性
//       //totalProperty: 'totalElements'  //一共多少条记录的属性
//       root: 'items'
//     },
//     simpleSortMode: true  //简单排序模式
//   },
//   pageSize: 25,
//   autoLoad: true,
//   remoteSort: true,//全局排序
//     sorters: {
//         direction: 'DESC',
//         property: 'id'
//     }
// });