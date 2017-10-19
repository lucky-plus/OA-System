Ext.define('Admin.view.myAssets.MyAssetsViewModel', {
	extend: 'Ext.app.ViewModel',
	alias: 'viewmodel.myAssetsViewModel',
	stores: {
		myAssetsLists: {
    type: 'myAssetsStore',//Store reference ==Store的属性 alias: 'store.myAssetsStore',		
    autoLoad: true //Auto load
}
}
});