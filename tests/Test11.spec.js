import { Selector,ClientFunction  } from 'testcafe';
import page from './method.po';

fixture `Test JQuery UI page`
.page `https://jqueryui.com/`

.beforeEach(async t => {
    await t.maximizeWindow()
    page.browserscroll();   
    const smenu  = Selector('a').withExactText('Selectmenu');
    await t.click(smenu);
});

const speed      = Selector('label[for=speed-button]');
const speedInput = Selector('#speed-button');
const txt1       = Selector('#speed-button > span.ui-selectmenu-text');
const txt2       = Selector('#files-button > span.ui-selectmenu-text'); 
const txt3       = Selector('#number-button > span.ui-selectmenu-text'); 
const txt4       = Selector('#salutation-button > span.ui-selectmenu-text');      
const lisp1      = Selector('#ui-id-1');
const lisp2      = Selector('#ui-id-5');
const liheader   = Selector('#files-menu > li:nth-child(1)');
const lihead     = Selector('#files-menu > li:nth-child(4)');
const lif       = Selector('#files-menu > li:nth-child(6)>div');
const files      = Selector('label[for=files-button]');
const filesInput = Selector('#files-button');
const number     = Selector('label[for=number-button]');
const numberInput= Selector('#number-button');
const lin1      = Selector('#ui-id-10');
const lin2      = Selector('#ui-id-15');
const lin3      = Selector('#ui-id-19');
const title      = Selector('label[for=salutation-button]');
const titleInput= Selector('#salutation-button');
const lit1      = Selector('#ui-id-2');
const lit2      = Selector('#ui-id-4');
const lit3      = Selector('#ui-id-6');

test('Select menu menu label checkbox', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame')
    await t.expect(await speed.innerText).contains('Select a speed');
    await t.expect(await files.innerText).contains('Select a file');
    await t.expect(await number.innerText).contains('Select a number');
    await t.expect(await title.innerText).contains('Select a title');
});
test('Select input visible text checkbox', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame')
    await t.expect(await speedInput.getAttribute('class')).notContains('disabled');
    await t.expect(await filesInput.getAttribute('class')).notContains('disabled');
    await t.expect(await numberInput.getAttribute('class')).notContains('disabled');
    await t.expect(await titleInput.getAttribute('class')).notContains('disabled');   
});
test('Speed Input test', async t => { await
    page.browserscroll();
    await t.switchToIframe('.demo-frame')
    await t.expect(await txt1.innerText).eql('Medium')
    .click(speedInput)
    .click(lisp1)
    await t.expect(await txt1.innerText).eql('Slower')
    .click(speedInput)
    .click(lisp2)
    await t.expect(await txt1.innerText).eql('Faster');
})
test('flie Input test', async t => { await
    page.browserscroll();
    await t.switchToIframe('.demo-frame')
    await t.expect(await txt2.innerText).eql('jQuery.js')
    .click(filesInput)
    await t.expect(await liheader.innerText).eql('Scripts')
    await t.expect(await lihead.innerText).eql('Other files');
    await t.click(filesInput)
    await t.expect(await txt2.innerText).eql('jQuery.js')
    .click(filesInput)
    .click(lif)
    await t.expect(await txt2.innerText).eql('Some other file with a very long option text');
})
test('Number Input test', async t => { await
    page.browserscroll();
    await t.switchToIframe('.demo-frame')
    await t.expect(await txt3.innerText).eql('2');
    await t.click(numberInput);
    await t.scrollIntoView(lin1);
    await t.click(lin1);
    await t.expect(await txt3.innerText).eql('10')
    await t.click(numberInput);
    await t.scrollIntoView(lin2);
    await t.click(lin2);
    await t.expect(await txt3.innerText).eql('15');
    await t.click(numberInput);
    await t.scrollIntoView(lin3);
    await t.click(lin3);
    await t.expect(await txt3.innerText).eql('19');
})
test('Title input test', async t => { await
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    await t.expect(await txt4.innerText).eql('Please pick one');
    await t.click(titleInput);
    await t.click(lit1);
    await t.expect(await txt4.innerText).eql('Mr.')
    await t.click(titleInput);
    await t.click(lit2);
    await t.expect(await txt4.innerText).eql('Dr.');
    await t.click(titleInput);
    await t.click(lit3);
    await t.expect(await txt4.innerText).eql('Other');
})