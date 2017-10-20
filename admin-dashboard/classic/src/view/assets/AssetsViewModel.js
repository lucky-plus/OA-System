Ext.define('Admin.view.assets.AssetsViewModel', {
	extend: 'Ext.app.ViewModel',
	alias: 'viewmodel.assetsViewModel',
	stores: {
		assetsLists: {
    type: 'assetsStore',//Store reference ==Store的属性 alias: 'store.assetsStore',		
    autoLoad: true //Auto load
}
}
});