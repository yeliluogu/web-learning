   const express = require('express');
   const path = require('path');
   const app = express();

   // 配置静态文件目录
   app.use(express.static(path.join(__dirname, 'src/main/webapp')));

   // 处理其他路由
   app.get('*', (req, res) => {
     res.sendFile(path.join(__dirname, 'src/main/webapp/index.html'));
   });

   app.listen(3000, () => {
     console.log('Server is running on http://localhost:3000');
   });
