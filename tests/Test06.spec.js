import { Selector } from 'testcafe';
import page from './method.po';


fixture `Test JQuery UI page`
.page `https://jqueryui.com/`

.beforeEach(async t => {
    await t.maximizeWindow()
    page.browserscroll();
    const checkBox  = Selector('a').withExactText('Controlgroup');
    await t.click(checkBox);
})
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe('.demo-frame');

    const dropdownName= Selector("#car-type-button > span.ui-selectmenu-text");
    const actual = await dropdownName.innerText;
    const dropdown    = Selector("#car-type-button");
    await t.expect(dropdown.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 })
    .expect(actual.toString()).eql('Compact car');
})
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const label1 = Selector('label[for=transmission-standard]');
    const radio1 = Selector('input[type=radio]').nth(0);
    const actual = await label1.innerText;
    await t.expect(radio1.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 })
    .expect(actual.toString()).eql(' Standard');
})
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const label2  = Selector('label[for=transmission-automatic]');
    const radio2  = Selector('input[type=radio]').nth(1);
    const actual  = await label2.innerText;
    await t.expect(radio2.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 })
    .expect(actual.toString()).eql(' Automatic');
})
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe('.demo-frame')
    const label3  = Selector('label[for=insurance]');
    const actual = await label3.innerText;
    await t.expect(actual.toString()).eql(' Insurance');
})
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const label4  = Selector('.ui-controlgroup-label-contents').nth(0);
    const actual = await label4.innerText;
    await t.expect(actual.toString()).eql('# of cars');
})
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe('.demo-frame')
    const btn = Selector('button'.withExactText('Book Now!'));
    const actual = await btn.innerText;
    await t.expect(actual).eql('Book Now!');
})
test('Dropdown length Test', async t => {
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const dropdown    = Selector("#car-type-button");
    await t.click(dropdown);
    const lists =  Selector('#car-type > option');
    await t.expect(await lists.count).eql(7);   
})
test('Dropdown Test -1', async t => { 
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const dropdown    = Selector("#car-type-button");
    await t.click(dropdown);
    const optn = Selector('#ui-id-4');
    await t.click(optn);
    await t
    .expect("SUV").eql(await dropdownName.innerText);
})
test('Dropdown Test -2', async t => { 
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const label1  = Selector('label[for=transmission-standard]');
    await t.click(label1)
    .expect(label1.classNames).contains('ui-checkboxradio-checked')
})
test('Dropdown Test -3', async t => { 
    page.browserscroll();
    await t .switchToIframe('.demo-frame')
    const label2  = Selector('label[for=transmission-automatic]');
    await t.click(label2)
    .expect(label2.classNames).contains('ui-checkboxradio-checked');
})
test('Dropdown Test -4', async t => { 
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const label3  = Selector('label[for=insurance]');
    await t.click(label3)
    .expect(label3.classNames).contains('ui-checkboxradio-checked');
})
test('Dropdown Test -5', async t => { 
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const hs   = Selector('#horizontal-spinner');
    const btn = Selector('button'.withExactText('Book Now!'));
    await t.click(hs)
    .typeText(hs,'2')
    await t.click(btn)
    .expect('2').eql(await hspinner.value); 
})
test('Dropdown Test -6', async t => { 
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const hs    = Selector('#horizontal-spinner');
    await t.click(hs)
    .typeText(hs,'e@#$999')
    await t.expect('e@#$999').eql(await hs.value,"Test case Pass"); 
})
test('Dropdown Test -7', async t => { 
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const hs    = Selector('#horizontal-spinner');
    const up    = Selector('a[role = button]').nth(0);
    await t.click(hs)
    .click(up)
    .expect('1').eql(await hs); 
})
test('Dropdown Test -8', async t => { 
    page.browserscroll()
    await t .switchToIframe('.demo-frame');
    const down  = Selector('a[role = button]').nth(2);
    const hs    = Selector('#horizontal-spinner');
    await t.click(hs)
    .click(down);
    await t
    .expect('-1').eql(await hs.value,"Test case fail"); 
})
test('Dropdown Test -8-1', async t => { 
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const btn = Selector('button'.withExactText('Book Now!'));
    await t.click(btn)
})
// .....................................
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe('.demo-frame')
    const actual = await dropdownName.innerText;
    await t.expect(dropdown2.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 })
    .expect(actual.toString()).eql('Compact car');
})
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const radio3    = Selector('input[type=radio]').nth(2);
    const label1v  = Selector('label[for=transmission-standard-v]');
    const actual = await label1v.innerText;
    await t.expect(radio3.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 })
    .expect(actual.toString()).eql(' Standard');
})
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const label2v  = Selector('label[for=transmission-automatic-v]');
    const radio4    = Selector('input[type=radio]').nth(3);
    const actual = await label2v.innerText;
    await t.expect(radio4.hasAttribute('d.isabled')).notOk('ready for testing', { timeout: 5000 })
    .expect(actual.toString()).eql(' Automatic');
})
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const label3v  = Selector('label[for=insurance-v]');
    const actual = await label3v.innerText;
    await t.expect(actual.toString()).eql(' Insurance');
})
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const label5  = Selector('.ui-controlgroup-label-contents').nth(1);
    const actual = await label5.innerText;
    await t.expect(actual.toString()).eql('# of cars');
})
test('Name test', async t =>{
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const btn2  = Selector('#book');
    const actual = await btn2.innerText;
    await t.expect(actual.toString()).eql('Book Now!');
})
test('Dropdown length Test', async t => {
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const dropdown2   = Selector("#ui-id-8");
    await t.click(dropdown2);
    const lists =  Selector('#ui-id-8 > li');
    await t.expect(await lists.count).eql(7);  
})
test('Dropdown Test -1', async t => { 
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const dropdown2   = Selector("#ui-id-8");
    await t.click(dropdown2);
    const optn = Selector('.ui-menu-item > #ui-id-15');
    await t.click(optn)
    .expect("Van").eql(await dropdownName.innerText);
})
test('Dropdown Test -2', async t => { 
    page.browserscroll();
    await t .switchToIframe(iframe);
    const label1v  = Selector('label[for=transmission-standard-v]');
    await t.click(label1v)
    .expect(label1v.classNames).contains('ui-checkboxradio-checked')
})
test('Dropdown Test -3', async t => { 
    page.browserscroll();
    await t .switchToIframe(iframe);
    const label2v  = Selector('label[for=transmission-automatic-v]');
    await t.click(label2v)
    .expect(label2v.classNames).contains('ui-checkboxradio-checked');
})
test('Dropdown Test -4', async t => { 
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const label3v  = Selector('label[for=insurance-v]');
    await t.click(label3v)
    .expect(label3v.classNames).contains('ui-checkboxradio-checked');
})
test('Dropdown Test -5', async t => { 
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const vs    = Selector('#vertical-spinner');
    const hs    = Selector('#harizontal-spinner');
    await t.click(vs)
    .typeText(vs,'2');
    await t
    .expect('2').eql(await hs.value); 
})
test('Dropdown Test -6', async t => { 
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const vs    = Selector('#vertical-spinner');
    const hs    = Selector('#harizontal-spinner');
    await t.click(vs)
    .typeText(vs,'e@#$999')
    await t
    .expect('e@#$999').eql(await hs.value,"Test case Pass"); 
})
test('Dropdown Test -7', async t => { 
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const up1    = Selector('a[role = button]').nth(1);
    const hs    = Selector('#harizontal-spinner');
    await t.click(vspinner)
    .click(up1);
    await t
    .expect(1).eql(await hs.value); 
})

test('Dropdown Test -8', async t => { 
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const down2  = Selector('a[role = button]').nth(3);
    const vs    = Selector('#vertical-spinner');
    const hs    = Selector('#harizontal-spinner');
    await t.click(vs)
    .click(down2);
    await t
    .expect('-1').eql(await hs.value,"Test case fail"); 
})
test('Dropdown Test -8-1', async t => { 
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const btn2  = Selector('#book');
    await t.click(btn2);
})
