1、删除脏数据 (以下是史家的脏数据)：
-- select * from t_documentflow_document where TITLE like '%测试%'
-- select * from t_documentflow_document where DOCUMENTSOURCEID='e410ff73543311e1839968b59979b43c' 发文单位为：测试单位
-- select * from t_documentflow_document where TITLE like '%111%'

set @result = '';
call proc_nbc_lib_Doucment_del('d04806076e2211e181b568b59979b43c',@result); -- 修改这里的参数, 0--代表删除失败 1--代表删除成功
select @result;

set @result = '';
call proc_nbc_lib_Doucment_del('295a8e93583f11e1b04b68b59979b43c',@result); -- 修改这里的参数, 0--代表删除失败 1--代表删除成功
select @result;

set @result = '';
call proc_nbc_lib_Doucment_del('472fe809543411e1839968b59979b43c',@result); -- 修改这里的参数, 0--代表删除失败 1--代表删除成功
select @result;

set @result = '';
call proc_nbc_lib_Doucment_del('6ba12cc9545a11e1839968b59979b43c',@result); -- 修改这里的参数, 0--代表删除失败 1--代表删除成功
select @result;

set @result = '';
call proc_nbc_lib_Doucment_del('9e727609584311e1b04b68b59979b43c',@result); -- 修改这里的参数, 0--代表删除失败 1--代表删除成功
select @result;

set @result = '';
call proc_nbc_lib_Doucment_del('f8dbd285578811e1940f68b59979b43c',@result); -- 修改这里的参数, 0--代表删除失败 1--代表删除成功
select @result;

set @result = '';
call proc_nbc_lib_Doucment_del('39777d44545911e1839968b59979b43c',@result); -- 修改这里的参数, 0--代表删除失败 1--代表删除成功
select @result;

set @result = '';
call proc_nbc_lib_Doucment_del('ad2af45fa63f11e2b442000c291b651f',@result); -- 修改这里的参数, 0--代表删除失败 1--代表删除成功
select @result;


2、执行迁移
set @result = '';
call proc_nbc_lib_Doucment_Migrate1(@result); -- 0 操作失败 1 操作成功
select @result;

set @result = '';
call proc_nbc_lib_Doucment_Migrate2(@result); -- 0 操作失败 1 操作成功
select @result;


3、添加配置：
#公文流转运行模式, true:实际环境	dev:开发测试
function.documentFlow.mode=dev
#公文流转
DOCUMENTFLOW.XIETONG.SWICH=true
DOCUMENTFLOW.PORTAL.SWICH=true
DOCUMENTFLOW.TYPE=type_07
DOCUMENTFLOW.CNNAME=公文流转

4、流转路径：
金强(待删除！)
李丽霞(待删除！)

