对于数据库表的基础操作包括“插入某一行”、“批量插入行”、“删除某一行”、“批量删除行”、“更新某一行”、“批量插入行时对已存在的行进行更新”、“选择某一行”、“选择某页行”。

为了将这些有着相同模式的代码与业务逻辑代码分离，将进行上述操作的各层抽离出来放在 com.gaotianchi.auth.base 包下，这些层包括：dto,
rest, converter, entity, service, dao, vo。

其中，dto 定义了请求体中待处理的数据结构以及对于属性值的验证规则，一个 dto 作为一个容器可以被多个 rest 方法使用。rest
作为控制器协调各层之间的工作。converter 作为 dto, entity 以及 vo 之间转换器提供映射服务。entity 与相关 mysql
表有一一对应关系。service 处理业务逻辑。dao 联合对应的 xml mapper 文件与 mysql dbs 交互负责实际的数据持久化以及查询操作。vo
和 dto 类似，定义了服务期望服务边界外看到的信息。

