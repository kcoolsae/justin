Events / categories
===
See also:

* [Home page](index.md)
* [Overview](overview.md)

---

An international workshop for which forms need to be filled out by the participants will further be called an *event*. Currently only
one event can be active at a time, although it should not be too hard to extend the system to allow for multiple events.

Users need to indicate explicitly whether they will attend an event (workshop) or not. A 'maybe' option can be provided
in the early stages of the registration process, but should be resolved before the event starts.

An event has an internal identifier (e.g., `2025`) which is used to refer to it in question forms (e.g., to provide default answers).

# Categories

An event has a number of associated *categories*, which each determine a set of questions (a form). Users can *subscribe* to zero or
more categories, and only need to answer the questionnaires for the categories they are subscribed to. Although again the system can easily be extended
for additional cases, currently only two categories  are implemented:

* Background information needed by the THC (`thc`)
* Accommodation preferences needed by the host (`host`)

Subscribing to a category serves a dual purpose

* Subscribing to `host` indicates that the user will attend the international workshop
* Subscribing to `thc` indicates that the user will join the task editing process at the workshop

In the user interface the latter information will be used to determine which forms need to be filled in, asking
the user to tick boxes with text like the following:

* I need accommodation at the workshop.
* I will participate in the task editing process at the workshop.

Regular participants should check both boxes, accompanying persons only the first one. A local participant that does not need accommodation but that
will join the task editing process should only check the second box.

{:.note}
Local participants from the organising host, that are not participants in the strict sense, but help with the organisation, 
need not necessarily register with the system. But then the host must make sure that they are included in the accommodation planning. 

# Event status

The status of an event can be one of the following:

* *In preparation* Administrators can create categories, forms, etc. for the event, but the event is not yet visible to regular users.
* *Published* Users can indicate whether they will attend the event or not, but questionnaires are not yet available.
* *Open* Users can fill out the questionnaires.
* *Closed* Users can no longer fill out the questionnaires.
* *Archived* The event is no longer visible to regular users.

