# Thought Dump Application

## NOTE: this project doesn't have the UI I have designed for it. 
### To see the intended UI, [click here](https://www.figma.com/proto/xT1n7AXV7rnPoaY95ttUDV/ThoughtDump?type=design&node-id=2-8&t=VDqcUf70JjaD9cvO-0&scaling=scale-down&page-id=0%3A1)

### What does this application do?

**This application is designed to be a place where you can "thought dump"**,
where you can write down anything that is on your mind. You can rant about whatever
is happening in your life, take little notes about the good or bad things that occurred during the day, or even
just taking a simple note to Google something later when you have the chance. It's meant to be a place where you can
write absolutely anything you want without worrying about odd privacy conflicts that might occur when saving something
to the cloud.

With each thought you write down, you can  choose to either **save it to a folder that you've named or throw it in the virtual trash** when you just want
to get something off of your chest.

### Who is the target audience for this application?

The target audience for Thought Dump is **students, particularly those who have a hard time collecting and dealing with
their thoughts and emotions**. However, I want to make the UI of this application intuitive enough for anyone to use.

### Why did I choose to do this project?

This project actually "solves" a problem of mine. I've recently started thought dumping in a new email, where all my
thoughts are just drafts that will never be sent as actual emails. However, I don't like using the interface of Gmail
to write down all my thoughts, and it feels a little weird having an email solely for the purpose of making drafts. This
project was mostly completed for the UBC course CPSC210: Software Construction, but I have worked on it after the course
completed.

### Challenges faced in the process

I had the most difficulties with creating the graphical interface. This project uses Java Swing, and I learned how to use
my resources when I am unsure of how to create the type of interface that I wanted. Specifically, I had the most difficult
time in created the window where the user creates a new note. I couldn't get the dimensions right of all the components
and I wasn't sure of what classes to use for each input. Through trial and error, I was able to get it to what it is today.
However, I am still unhappy with how the interface looks.

### Future changes
In the future, I would like to change the UI. With my previous knowledge experience as a UX designer, the current UI is
quite outdated.

I would also like to implement a feature where each note can be associated with an emoji to further help users recognize
and manage their emotions.

### User Stories
- As a user, I want to be able to write down a note and throw it away
- As a user, I want to be able to write down a note and save it to a folder
- As a user, I want to be able to edit an existing notes
- As a user, I want to delete an existing note
- As a user, I want to be able to create folders
- As a user, I want to be able to look at all my notes in a folder
- As a user, when I select the quit option from the application menu, I want to be reminded that I have the option to
  save my recent changes to file.
- As a user, when I start the application, I want to be given the option to load my notes and folders from file.