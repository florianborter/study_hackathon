import router from './router.js';
import homeComp from './components/home.js';
import chatComp from './components/chat.js';

router.register('/', homeComp)
router.register('/chat', chatComp);

router.navigate('/');
