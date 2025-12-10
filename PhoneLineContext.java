
public final class PhoneLineContext
    extends FSMContext
{
//---------------------------------------------------------------
// Member methods.
//

    public PhoneLineContext(PhoneLine owner)
    {
        super();

        _owner = owner;
        setState(PhoneLineFSM.Idle);
        PhoneLineFSM.Idle.Entry(this);
    }

    public void invalidNumber()
    {
        _transition = "invalidNumber";
        getState().invalidNumber(this);
        _transition = "";
        return;
    }

    public void offHook()
    {
        _transition = "offHook";
        getState().offHook(this);
        _transition = "";
        return;
    }

    public void onHook()
    {
        _transition = "onHook";
        getState().onHook(this);
        _transition = "";
        return;
    }

    public void validNumber()
    {
        _transition = "validNumber";
        getState().validNumber(this);
        _transition = "";
        return;
    }

    public PhoneLineState getState()
        throws StateUndefinedException
    {
        if (_state == null)
        {
            throw(
                new StateUndefinedException());
        }

        return ((PhoneLineState) _state);
    }

    protected PhoneLine getOwner()
    {
        return (_owner);
    }

//---------------------------------------------------------------
// Member data.
//

    transient private PhoneLine _owner;

//---------------------------------------------------------------
// Inner classes.
//

    public static abstract class PhoneLineState
        extends State
    {
    //-----------------------------------------------------------
    // Member methods.
    //

        protected PhoneLineState(String name, int id)
        {
            super (name, id);
        }

        protected void Entry(PhoneLineContext context) {}
        protected void Exit(PhoneLineContext context) {}

        protected void invalidNumber(PhoneLineContext context)
        {
            Default(context);
        }

        protected void offHook(PhoneLineContext context)
        {
            Default(context);
        }

        protected void onHook(PhoneLineContext context)
        {
            Default(context);
        }

        protected void validNumber(PhoneLineContext context)
        {
            Default(context);
        }

        protected void Default(PhoneLineContext context)
        {
            throw (
                new TransitionUndefinedException(
                    "State: " +
                    context.getState().getName() +
                    ", Transition: " +
                    context.getTransition()));
        }

    //-----------------------------------------------------------
    // Member data.
    //
    }

    /* package */ static abstract class PhoneLineFSM
    {
    //-----------------------------------------------------------
    // Member methods.
    //

    //-----------------------------------------------------------
    // Member data.
    //

        //-------------------------------------------------------
        // Statics.
        //
        public static PhoneLineFSM_Default.PhoneLineFSM_Idle Idle;
        public static PhoneLineFSM_Default.PhoneLineFSM_Ready Ready;
        public static PhoneLineFSM_Default.PhoneLineFSM_Conversation Conversation;
        public static PhoneLineFSM_Default.PhoneLineFSM_Warning Warning;
        private static PhoneLineFSM_Default Default;

        static
        {
            Idle = new PhoneLineFSM_Default.PhoneLineFSM_Idle("PhoneLineFSM.Idle", 4);
            Ready = new PhoneLineFSM_Default.PhoneLineFSM_Ready("PhoneLineFSM.Ready", 5);
            Conversation = new PhoneLineFSM_Default.PhoneLineFSM_Conversation("PhoneLineFSM.Conversation", 6);
            Warning = new PhoneLineFSM_Default.PhoneLineFSM_Warning("PhoneLineFSM.Warning", 7);
            Default = new PhoneLineFSM_Default("PhoneLineFSM.Default", -1);
        }

    }

    protected static class PhoneLineFSM_Default
        extends PhoneLineState
    {
    //-----------------------------------------------------------
    // Member methods.
    //

        protected PhoneLineFSM_Default(String name, int id)
        {
            super (name, id);
        }

    //-----------------------------------------------------------
    // Inner classse.
    //


        private static final class PhoneLineFSM_Idle
            extends PhoneLineFSM_Default
        {
        //-------------------------------------------------------
        // Member methods.
        //

            private PhoneLineFSM_Idle(String name, int id)
            {
                super (name, id);
            }

            protected void offHook(PhoneLineContext context)
            {
                PhoneLine ctxt = context.getOwner();


                (context.getState()).Exit(context);
                context.clearState();
                try
                {
                    ctxt.soundDialTone();
                }
                finally
                {
                    context.setState(PhoneLineFSM.Ready);
                    (context.getState()).Entry(context);
                }
                return;
            }

        //-------------------------------------------------------
        // Member data.
        //
        }

        private static final class PhoneLineFSM_Ready
            extends PhoneLineFSM_Default
        {
        //-------------------------------------------------------
        // Member methods.
        //

            private PhoneLineFSM_Ready(String name, int id)
            {
                super (name, id);
            }

            protected void Entry(PhoneLineContext context)
            {
                PhoneLine ctxt = context.getOwner();

                ctxt.startTimer();
                return;
            }

            protected void Exit(PhoneLineContext context)
            {
                PhoneLine ctxt = context.getOwner();

                ctxt.stopTimer();
                return;
            }

            protected void invalidNumber(PhoneLineContext context)
            {
                PhoneLine ctxt = context.getOwner();


                (context.getState()).Exit(context);
                context.clearState();
                try
                {
                    ctxt.PlayMessage();
                }
                finally
                {
                    context.setState(PhoneLineFSM.Warning);
                    (context.getState()).Entry(context);
                }
                return;
            }

            protected void onHook(PhoneLineContext context)
            {
                PhoneLine ctxt = context.getOwner();


                (context.getState()).Exit(context);
                context.clearState();
                try
                {
                    ctxt.disconnectedLine();
                }
                finally
                {
                    context.setState(PhoneLineFSM.Idle);
                    (context.getState()).Entry(context);
                }
                return;
            }

            protected void validNumber(PhoneLineContext context)
            {
                PhoneLine ctxt = context.getOwner();


                (context.getState()).Exit(context);
                context.clearState();
                try
                {
                    ctxt.findConnection();
                }
                finally
                {
                    context.setState(PhoneLineFSM.Conversation);
                    (context.getState()).Entry(context);
                }
                return;
            }

        //-------------------------------------------------------
        // Member data.
        //
        }

        private static final class PhoneLineFSM_Conversation
            extends PhoneLineFSM_Default
        {
        //-------------------------------------------------------
        // Member methods.
        //

            private PhoneLineFSM_Conversation(String name, int id)
            {
                super (name, id);
            }

            protected void invalidNumber(PhoneLineContext context)
            {
                PhoneLine ctxt = context.getOwner();

                PhoneLineState endState = context.getState();

                context.clearState();
                try
                {
                    ctxt.continues();
                }
                finally
                {
                    context.setState(endState);
                }
                return;
            }

            protected void onHook(PhoneLineContext context)
            {
                PhoneLine ctxt = context.getOwner();


                (context.getState()).Exit(context);
                context.clearState();
                try
                {
                    ctxt.disconnectedLine();
                }
                finally
                {
                    context.setState(PhoneLineFSM.Idle);
                    (context.getState()).Entry(context);
                }
                return;
            }

            protected void validNumber(PhoneLineContext context)
            {
                PhoneLine ctxt = context.getOwner();

                PhoneLineState endState = context.getState();

                context.clearState();
                try
                {
                    ctxt.continues();
                }
                finally
                {
                    context.setState(endState);
                }
                return;
            }

        //-------------------------------------------------------
        // Member data.
        //
        }

        private static final class PhoneLineFSM_Warning
            extends PhoneLineFSM_Default
        {
        //-------------------------------------------------------
        // Member methods.
        //

            private PhoneLineFSM_Warning(String name, int id)
            {
                super (name, id);
            }

            protected void invalidNumber(PhoneLineContext context)
            {
                PhoneLine ctxt = context.getOwner();

                PhoneLineState endState = context.getState();

                context.clearState();
                try
                {
                    ctxt.slowBusyTone();
                }
                finally
                {
                    context.setState(endState);
                }
                return;
            }

            protected void onHook(PhoneLineContext context)
            {
                PhoneLine ctxt = context.getOwner();


                (context.getState()).Exit(context);
                context.clearState();
                try
                {
                    ctxt.disconnectedLine();
                }
                finally
                {
                    context.setState(PhoneLineFSM.Idle);
                    (context.getState()).Entry(context);
                }
                return;
            }

            protected void validNumber(PhoneLineContext context)
            {
                PhoneLine ctxt = context.getOwner();

                PhoneLineState endState = context.getState();

                context.clearState();
                try
                {
                    ctxt.slowBusyTone();
                }
                finally
                {
                    context.setState(endState);
                }
                return;
            }

        //-------------------------------------------------------
        // Member data.
        //
        }

    //-----------------------------------------------------------
    // Member data.
    //
    }
}
