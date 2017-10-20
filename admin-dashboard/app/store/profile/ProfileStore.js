Ext.define('Admin.view.profile.ProfileStore', {
  extend: 'Ext.data.Store',
    alias: 'store.profileStore',			  //1.Store取别名（reference）
    model: 'Admin.model.profile.ProfileModel',//2.设置model的全路径
    proxy: {
      type: 'ajax',
		url: 'staff/findUserByUserId.json?userId='+loginUserId,	//后台AssetsController中的接口url地址
		reader: {
			type:'json',
			rootProperty: 'content',		//结果集名字的属性
			totalProperty: 'totalElements'	//一共多少条记录的属性
		}
	}
});